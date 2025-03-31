package com.leehe228.eweek04a.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leehe228.eweek04a.model.Item
import com.leehe228.eweek04a.model.ToDoListFactory
import java.nio.file.WatchEvent

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val todoList = remember {
        // mutableStateListOf<Item>() // 빈 리스트
        ToDoListFactory.makeToDoList()
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        ToDoListTitle()
        Spacer(modifier = Modifier.height(8.dp))
        ToDoList(
            todoList = todoList,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        ToDoItemInput(todoList = todoList)
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
