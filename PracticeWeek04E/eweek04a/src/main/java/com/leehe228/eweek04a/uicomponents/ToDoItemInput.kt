package com.leehe228.eweek04a.uicomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // by 사용 시 getValue import 필요
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue // by 사용 시 setValue import 필요
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leehe228.eweek04a.model.Item
import com.leehe228.eweek04a.model.ToDoListFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ToDoItemInput(todoList: MutableList<Item>, modifier: Modifier = Modifier) {
    // State
    var textFieldState by remember { mutableStateOf("") }

    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = textFieldState,
            onValueChange = { text: String -> textFieldState = text },
            // onValueChange = { textFieldState = it }, // text 매개변수 생략하고 it으로 받음
            placeholder = { Text("할 일을 입력하세요.") },
            modifier = modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(onClick = {
            // 텍스트
            val text = textFieldState

            // 현재 시간
            val currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))

            // 리스트에 아이템 추가
            todoList.add(Item(text, currentTime))

            // TextField 텍스트 초기화
            textFieldState = ""
        }) {
            Text("추가")
        }
    }
}

@Preview
@Composable
private fun ToDoItemInputPreview() {
    ToDoItemInput(todoList = ToDoListFactory.makeToDoList())
}
