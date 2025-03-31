package com.leehe228.eweek04a.uicomponents

import android.R.attr.text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ToDoSwitch(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = text)
        Spacer(modifier = Modifier.width(8.dp))
        Switch(checked = checked, onCheckedChange = { checked -> onCheckedChange(checked) })
    }
}

@Preview
@Composable
private fun ToDoSwitchPreview() {
    ToDoSwitch(text = "미완료 항목만 보기", checked = true, onCheckedChange = {})
}
