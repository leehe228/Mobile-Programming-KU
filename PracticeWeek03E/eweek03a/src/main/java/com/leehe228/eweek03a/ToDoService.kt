package com.leehe228.eweek03a

import java.time.LocalDateTime
import com.leehe228.eweek03a.model.Item
import com.leehe228.eweek03a.model.ToDoStatus
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

    fun getListSize() = todoList.size

    fun printOneMemoByIndex(idx: Int = 0) {
        println(todoList[idx])
    }

    fun setStatus(idx: Int, status: ToDoStatus) {
        todoList[idx].status = status
    }

    fun searchByContainString(keyword: String) {
        var count: Int = 0

        todoList.forEachIndexed { index, item ->
            if (item.content.contains(keyword)) {
                count++
                println("$index : $item")
            }
        }

        if (count == 0) {
            println("검색 결과가 없습니다.")
        }
    }
}
