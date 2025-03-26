package com.leehe228.eweek04a.uicomponents

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.leehe228.eweek04a.R

@Composable
fun ToDoListTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.todolist_title),
        fontSize = 24.sp
    )
}

@Preview
@Composable
private fun ToDoListPreview() {
    ToDoListTitle()
}