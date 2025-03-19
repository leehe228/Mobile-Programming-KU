package com.leehe228.eweek03a

import com.leehe228.eweek03a.model.Item
import com.leehe228.eweek03a.model.ToDoListFactory
import com.leehe228.eweek03a.model.ToDoStatus

fun main() {
    // Practice 01
//    val item1 = Item("모프 공부하기1", "03-19 12:50")
//    val item2 = Item("모프 공부하기2", "03-19 12:50", ToDoStatus.COMPLETED)
//
//    println(item1)
//    println(item2)

    // Practice 02
//    val todoList = ToDoListFactory.makeToDoList()
//
//    if (todoList.isEmpty()) {
//        println("등록된 일정이 없습니다.")
//    } else {
//        todoList.forEachIndexed { index, item ->
//            println("$index : $item")
//        }
//    }

    // Practice 03
    // val todoService = ToDoService(mutableListOf<Item>())
    val todoService = ToDoService(ToDoListFactory.makeToDoList())

    // e.g.
    todoService.addContent("모프 공부하기7")

    // print all
    todoService.listAllToDo()
}
