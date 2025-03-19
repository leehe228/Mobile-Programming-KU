package com.leehe228.eweek03a.model

data class Item(
    val content: String,
    val time: String,
    var status: ToDoStatus = ToDoStatus.PENDING
) {
    override fun toString(): String {
        return "${content}\t${time}\t${}"
    }
}
