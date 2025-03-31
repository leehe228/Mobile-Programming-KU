package com.leehe228.eweek04a.model

import androidx.compose.runtime.mutableStateListOf

object ToDoListFactory {
    // return empty Item list
    // fun makeToDoList() = mutableListOf<Item>(
    fun makeToDoList() = mutableStateListOf<Item> ( // State List로 변경
        Item("아침 명상하기", "03-19 05:30", ToDoStatus.COMPLETED),
        Item("오전 운동", "03-19 06:30"),
        Item("책 읽기", "03-19 08:30"),
        Item("점심 먹기", "03-19 12:30", ToDoStatus.COMPLETED),
        Item("모프 공부하기", "03-19 17:30"),
    )
}
