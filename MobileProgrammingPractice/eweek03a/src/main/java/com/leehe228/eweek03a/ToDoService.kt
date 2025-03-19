package com.leehe228.eweek03a

import java.time.LocalDateTime
import com.leehe228.eweek03a.model.Item
import java.time.format.DateTimeFormatter

class ToDoService(val todoList: MutableList<Item>) {
    fun addContent(content: String) {
        // current datetime to string
        val currentTime = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))

        // add to list
        todoList.add(Item(content, currentTime))
    }

    fun listAllToDo() {
        if (todoList.isEmpty()) {
            println("등록된 일정이 없습니다.")
        } else {
            todoList.forEachIndexed { index, item ->
                println("$index : $item")
            }
        }
    }
}
