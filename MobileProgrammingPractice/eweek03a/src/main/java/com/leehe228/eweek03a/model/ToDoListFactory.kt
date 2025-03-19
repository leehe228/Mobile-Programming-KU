package com.leehe228.eweek03a.model

object ToDoListFactory {
    fun makeToDoList() = mutableListOf(
        Item("모프 공부하기1", "03-19 13:00"),
        Item("모프 공부하기2", "03-19 13:10", ToDoStatus.COMPLETED),
        Item("모프 공부하기3", "03-19 13:20", ToDoStatus.COMPLETED),
        Item("모프 공부하기4", "03-19 13:30"),
    )
}
