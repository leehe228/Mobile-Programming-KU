package com.leehe228.eweek03a

import com.leehe228.eweek03a.model.Item
import com.leehe228.eweek03a.model.ToDoListFactory
import com.leehe228.eweek03a.model.ToDoStatus

fun printMenu() {
    val menuString: String = """===== TodoList 메뉴 =====
1. 메모 등록
2. 메모 완료 체크
3. 메모 검색
4. 메모 전체 리스트 보기
5. 종료"""
    println(menuString)
}

fun AddMemo(todoService: ToDoService) {
    print("메모 등록: ")
    val content: String = readln().trim()

    todoService.addContent(content)

    println("메모 등록 완료")
}

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
    // todoService.addContent("모프 공부하기7")

    // print all
    // todoService.listAllToDo()

    var slc: Int = 0
    do {
        printMenu()
        print("메뉴 선택: ")

        // read menu input
        val slc: Int = readlnOrNull()?.toIntOrNull() ?: 0

        // out of range
        if (slc < 1 || slc > 5) continue

        when (slc) {
            1 -> {

            }
            2 -> {

            }
            3 -> {

            }
            4 -> {
                println("전체 메모:")
                todoService.listAllToDo()
            }
        }

    } while (slc != 5)
}
