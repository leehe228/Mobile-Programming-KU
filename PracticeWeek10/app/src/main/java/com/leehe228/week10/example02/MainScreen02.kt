package com.leehe228.week10.example02

import android.Manifest
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.leehe228.week10.functions.makeCall
import com.leehe228.week10.uicomponents.RationaleCallDialog
import com.leehe228.week10.uicomponents.SettingCallDialog

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen02(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val callPermissionState = rememberPermissionState(permission = Manifest.permission.CALL_PHONE)
    var showCallDialog by remember { mutableStateOf(false) }
    var showSettingDialog by remember { mutableStateOf(false) }
    var permissionConfirm by remember { mutableStateOf(false) } // 권한 승인을 한 번이라도 받았는지

    fun requestCallPermission() {
        when {
            callPermissionState.status.isGranted -> {
                // 권한 승인 받았으면 전화 걸기
                makeCall(context)
            }

            callPermissionState.status.shouldShowRationale -> {
                // 설명 보여주기
                showCallDialog = true
            }

            else -> {
                if (permissionConfirm) {
                    showSettingDialog = true
                } else {
                    // 권한 요청이 처음인 경우
                    permissionConfirm = true
                    callPermissionState.launchPermissionRequest() // 요청
                }
            }
        }
    }

    if (showCallDialog) {
        RationaleCallDialog(
            onDismiss = { showCallDialog = false },
            onConfirm = {
                showCallDialog = false
                callPermissionState.launchPermissionRequest()
            }
        )
    }
    
    if (showSettingDialog) {
        SettingCallDialog(
            onDismiss = { showSettingDialog = false },
            onGoToSettings = {
                showSettingDialog = false
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = "package:${context.packageName}".toUri()
                }
                context.startActivity(intent)
            }
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Button(onClick = {
//            val web = Uri.parse("https://www.naver.com")
//            val webIntent = Intent(Intent.ACTION_VIEW, web)
            val webIntent = Intent(Intent.ACTION_VIEW).apply {
                // Intent.setData = Uri.parse("https://www.naver.com")
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

        Button(onClick = {
            requestCallPermission()
//            val number = "tel:010-1234-1234".toUri()
//            val callIntent = Intent(Intent.ACTION_DIAL, number)
//            context.startActivity(callIntent)
        }, modifier = Modifier.width(200.dp)) {
            Text("전화 걸기")
        }
    }
}

@Preview
@Composable
private fun MainScreen02Preview() {
    MainScreen02()
}
