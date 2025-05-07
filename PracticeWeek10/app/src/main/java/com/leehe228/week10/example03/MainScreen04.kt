package com.leehe228.week10.example03

import android.Manifest
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.leehe228.week10.functions.makeCall
import com.leehe228.week10.functions.showCamera
import com.leehe228.week10.uicomponents.PermissionButton
import com.leehe228.week10.uicomponents.PermissionButton2

@Composable
fun MainScreen04(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            val webIntent = Intent(Intent.ACTION_VIEW).apply {
                data = "https://www.naver.com".toUri()
            }
            context.startActivity(webIntent)
        }, modifier = Modifier.width(200.dp)) {
            Text("네이버")
        }

        Button(onClick = {
            val location = "geo:37.543684,127.077130?z=16".toUri()
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            context.startActivity(mapIntent)
        }, modifier = Modifier.width(200.dp)) {
            Text("맵")
        }

        Button(onClick = {
            val message = "sms:010-1234-1234".toUri()
            val messageIntent = Intent(Intent.ACTION_SENDTO, message)
            messageIntent.putExtra("sms_body", "집에 가자....")
            context.startActivity(messageIntent)
        }, modifier = Modifier.width(200.dp)) {
            Text("문자 보내기")
        }

        PermissionButton2(
            permission = Manifest.permission.CALL_PHONE,
            label = "전화 걸기",
            onGranted = { makeCall(context) }
        )

        PermissionButton2(
            permission = Manifest.permission.CAMERA,
            label = "카메라",
            onGranted = { showCamera(context) }
        )
    }
}
