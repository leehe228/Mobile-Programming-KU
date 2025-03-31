package com.leehe228.eweek04a.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leehe228.eweek04a.model.Item
import com.leehe228.eweek04a.model.ToDoListFactory
import com.leehe228.eweek04a.model.ToDoStatus

@Composable
fun ToDoList(todoList: MutableList<Item>, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        todoList.forEachIndexed { index, item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    /* ToDoCheckbox(checked= item.status == ToDoStatus.COMPLETED, onCheckedChange = {

                    }) */
                    ToDoCheckbox(checked = item.status == ToDoStatus.COMPLETED) { isChecked -> // it 대신 isChecked로 받음
                        // 모든 매개변수는 immutable (변경 불가 객체)
                        // immutable이므로 업데이트 불가
                        // item.status = ToDoStatus.COMPLETED

                        // 리스트가 state여도 속성 변경되어도 리스트 변경으로 감지 불가함
                        // 새로운 객체로 바꾸어주어야 함
                        // todoList[index].status = ToDoStatus.COMPLETED// immutable이므로 업데이트 불가
                        // item.status = ToDoStatus.COMPLETED

                        // 리스트가 state여도 속성 변경되어도 리스트 변경으로 감지 불가함
                        // 새로운 객체로 바꾸어주어야 함
                        // todoList[index].status = ToDoStatus.COMPLETED

                        // 리스트를 갱신시키기 위해 아이템 객체 자체를 바꿈
                        todoList[index] = item.copy(
                            status = if (isChecked) ToDoStatus.COMPLETED
                            else ToDoStatus.PENDING
                        )
                    }
                    ToDoItem(item = item)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ToDoListPreview() {
    ToDoList(ToDoListFactory.makeToDoList())
}
