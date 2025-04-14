package com.leehe228.eweek05a.uiexamples

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leehe228.eweek05a.uiexamples.ScrollToTopButton
import com.leehe228.eweek05a.uiexamples.TextCell
import kotlinx.coroutines.launch

@Composable
fun TextLazyColumnFAB(dataList: MutableList<String>, modifier: Modifier = Modifier) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val showButton by remember {
        derivedStateOf { // mutableStateOf로 선언 시 초기에 if문 결과값만 저장됨 (false값 가진 상태)
            state.firstVisibleItemIndex > 0 // LazyList에서 맨 위 (처음) 보여지고 있는 item 인덱스
        }
    }
    // val showButton = state.firstVisibleItemIndex > 0 

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = state,
            modifier = modifier,
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = dataList) { item ->
                TextCell(text = item, Modifier.background(Color.Green))
            }
        }
        AnimatedVisibility(visible = showButton) {
            ScrollToTopButton {
                scope.launch {
                    state.scrollToItem(0) // 상단으로 이동, suspend 함수
                }
            }
        }
    }
}

@Preview
@Composable
private fun TextLazyColumnFABPreview() {
    val dataList = (1..15).map { it.toString() }.toMutableList()
    TextLazyColumnFAB(dataList = dataList, modifier = Modifier.fillMaxSize())
}
