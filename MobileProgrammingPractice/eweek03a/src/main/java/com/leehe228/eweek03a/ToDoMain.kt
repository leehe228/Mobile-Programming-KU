package com.leehe228.eweek03a

import com.leehe228.eweek03a.model.Item
import com.leehe228.eweek03a.model.ToDoStatus

fun main() {
    val item1 = Item("모프 공부하기1", "03-19 12:50")
    val item2 = Item("모프 공부하기2", "03-19 12:50", ToDoStatus.COMPLETED)

    println(item1)
    println(item2)
}
