package com.leehe228.week11.example02
// 설치 라이브러리
// com.squareup.retrofit2:retrofit2
// com.squareup.retrofit2:converter-gson

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

sealed class ChatMessage(open val text: String) {
    data class User(override val text: String) : ChatMessage(text)
    data class Assistant(override val text: String) : ChatMessage(text)
}

data class MessageDto(val role: String, val content: String)
data class RequestDto(
    val model: String,
    val messages: List<MessageDto>,
    val max_tokens: Int? = null,
    val temperature: Double? = null
)

data class ChoiceDto(val message: MessageDto)
data class ResponseDto(val choices: List<ChoiceDto>)

interface OpenAIService {
    @Headers(
        "Content-Type: application/json", "Authorization: Bearer <OPENAI_API_KEY>" // TODO
    )
    @POST("chat/completions")
    fun getChatCompletion(@Body request: RequestDto): Call<ResponseDto>
}

object RetrofitClient {
    val service: OpenAIService by lazy {
        Retrofit.Builder().baseUrl("https://api.openai.com/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(OpenAIService::class.java)
    }
}

class ChatGptRepository {
    suspend fun sendMessage(history: List<ChatMessage>): String {
        val messages = history.map {
            val role = when (it) {
                is ChatMessage.User -> "user"
                is ChatMessage.Assistant -> "assistant"
            }
            MessageDto(role, it.text)
        }
        val requestDto = RequestDto(
            model = "gpt-4.1-nano", messages = messages, max_tokens = 100, temperature = 0.7
        )
        return try {
            //  val response = RetrofitClient.service.getChatCompletion(requestDto).execute()
            val response = withContext(Dispatchers.IO) {
                RetrofitClient.service.getChatCompletion(requestDto).execute()
            }
            if (response.isSuccessful) {
                response.body()?.choices?.firstOrNull()?.message?.content ?: "답변이 없습니다."
            } else {
                when (response.code()) {
                    401 -> "인증 오류(API 키 확인 필요)"
                    429 -> "요청이 너무 많습니다. 잠시 후 다시 시도하세요."
                    500 -> "서버 오류가 발생했습니다."
                    else -> "API 호출 실패: ${response.code()}"
                }
            }
        } catch (e: Exception) {
            Log.e("ChatGptService", "Error", e)
            "오류: ${e.message}"
        }
    }
}

class ChatGptViewModel(private val chatGptRepository: ChatGptRepository) : ViewModel() {

    private val _history = MutableStateFlow<List<ChatMessage>>(emptyList())
    val history = _history.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun send(text: String) {
        if (text.isBlank()) return
        _history.value = _history.value + ChatMessage.User(text)
        _loading.value = true

        viewModelScope.launch {
            val reply = chatGptRepository.sendMessage(_history.value)
            _history.value = _history.value + ChatMessage.Assistant(reply)
            _loading.value = false
        }
    }
}

class ChatViewModelFactory(private val chatGptRepository: ChatGptRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatGptViewModel::class.java)) {
            return ChatGptViewModel(chatGptRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun ChatScreen() {

    val viewModel: ChatGptViewModel = viewModel(factory = ChatViewModelFactory(ChatGptRepository()))
    val messages by viewModel.history.collectAsState()
    val isLoading by viewModel.loading.collectAsState()

    var input by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            Modifier
                .weight(1f)
                .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { msg ->
                val isUser = msg is ChatMessage.User
                val bgColor = if (isUser) Color(0xFFB2DFDB) else Color(0xFFE1BEE7)
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
                ) {
                    Box(
                        Modifier
                            .background(bgColor)
                            .padding(12.dp)
                    ) {
                        Text(msg.text)
                    }
                }
            }
            if (isLoading) {
                item {
                    Row(
                        Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        TextField(
            value = input,
            onValueChange = { input = it },
            placeholder = { Text("메시지 입력...") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        viewModel.send(input)
                        input = ""
                    }, enabled = input.isNotBlank() && !isLoading
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "send")
                }
            })
    }
}
