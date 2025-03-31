package com.leehe228.eweek04a.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leehe228.eweek04a.R
import com.leehe228.eweek04a.model.Item
import com.leehe228.eweek04a.model.ToDoListFactory
import java.nio.file.WatchEvent

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val todoList = remember {
        // mutableStateListOf<Item>() // 빈 리스트
        ToDoListFactory.makeToDoList()
    }
    val (switchChecked, setSwitchChecked) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ToDoListTitle()
        // 이름 학번 출력
        Text(stringResource(R.string.student_info))
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            ToDoSwitch(
                modifier = modifier,
                text = stringResource(R.string.show_only_pending),
                checked = switchChecked
            ) { // 익명 함수가 마지막 매개변수이므로 중괄호로 뺄 수 있음
                setSwitchChecked(it) // 매개변수 1개이므로 it으로 받음
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ToDoList(
            todoList = todoList,
            showOnlyPending = switchChecked,
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
