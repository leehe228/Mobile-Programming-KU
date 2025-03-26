package com.leehe228.eweek04a.uicomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // by 사용 시 getValue import 필요
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue // by 사용 시 setValue import 필요
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leehe228.eweek04a.model.Item
import com.leehe228.eweek04a.model.ToDoListFactory

@Composable
fun ToDoItemInput(todoList: MutableList<Item>, modifier: Modifier = Modifier) {
    // State
    var textFieldState by remember { mutableStateOf("") }

    Row {
        TextField(
            value = textFieldState,
            onValueChange = { text: String -> textFieldState = text },
            // onValueChange = { textFieldState = it }, // text 매개변수 생략하고 it으로 받음
            placeholder = { Text("할 일을 입력하세요.") }
        )

        Button(onClick = {
            val text = textFieldState
            println(text)
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
