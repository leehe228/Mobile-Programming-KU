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

// Menu 1
fun addMemo(todoService: ToDoService) {
    print("메모 등록: ")
    val content: String = readln().trim()

    todoService.addContent(content)

    print("메모 등록됨: ")
    todoService.printOneMemoByIndex(todoService.getListSize() - 1)
}

// Menu 2
fun checkComplete(todoService: ToDoService) {
    printAllMemo(todoService)
    print("완료 처리할 메모의 인덱스를 입력하세요: ")

    val idx: Int = readlnOrNull()?.toIntOrNull() ?: 0

    // index out of range
    if (idx < 0 || idx >= todoService.getListSize()) {
        println("인덱스의 범위가 잘못되었습니다.")
        return
    }

    // already completed
    if (todoService.todoList[idx].status == ToDoStatus.COMPLETED) {
        println("해당 인덱스의 메모는 이미 완료되었습니다.")
        return
    }

    // set status to COMPLETED
    todoService.setStatus(idx, ToDoStatus.COMPLETED)

    print("메모 완료 처리됨: ")
    todoService.printOneMemoByIndex(idx)
}

// Menu 3
fun searchMemo(todoService: ToDoService) {
    print("검색할 키워드를 입력하세요: ")
    val keyword: String = (readlnOrNull() ?: "").trim()

    if (keyword.isEmpty()) {
        println("키워드가 입력되지 않았습니다.")
        return
    }

    println("검색 결과: ")
    todoService.searchByContainString(keyword)
}

// Menu 4
fun printAllMemo(todoService: ToDoService) {
    println("전체 메모:")
    todoService.listAllToDo()
}

fun main() {
    val todoService = ToDoService(ToDoListFactory.makeToDoList())

    println("=========================")
    println("랩실습 3주차 A")
    println("컴퓨터공학부 202011353 이호은")
    println("=========================")

    do {
        printMenu()
        print("메뉴 선택: ")

        // read menu input
        val slc: Int = readlnOrNull()?.toIntOrNull() ?: 0

        // out of range
        if (slc < 1 || slc > 5) continue

        when (slc) {
            1 -> {
                addMemo(todoService)
            }
            2 -> {
                checkComplete(todoService)
            }
            3 -> {
                searchMemo(todoService)
            }
            4 -> {
                printAllMemo(todoService)
            }
        }

    } while (slc != 5)
}
