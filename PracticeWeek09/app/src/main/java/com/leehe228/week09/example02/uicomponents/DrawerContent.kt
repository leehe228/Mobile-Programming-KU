package com.leehe228.week09.example02.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leehe228.week09.R

@Composable
fun ColumnScope.DrawerContent(modifier: Modifier = Modifier) {
    Text(
        text = "202011353 이호은",
        modifier = Modifier.padding(16.dp),
        fontSize = 20.sp
    )

    // 실습 - 본인 사진 추가
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.image),
            contentDescription = ""
        )
    }

    Text(
        text = "leehe228@konkuk.ac.kr",
        modifier = Modifier.padding(16.dp),
        fontSize = 20.sp
    )

    NavigationDrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Drawer Item 1") },
        icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") },
        onClick = { },
        selected = true
    )

    Spacer(modifier = Modifier.height(8.dp))

    NavigationDrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Drawer Item 2") },
        icon = { },
        onClick = { },
        selected = true
    )
}
