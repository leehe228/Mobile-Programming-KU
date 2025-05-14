package com.leehe228.week10.example06

import android.R.attr.defaultValue
import android.R.attr.type
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NotificationNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Main") {
        composable("Main") {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NotificationMainScreen()
            }
        }
        composable(
            route = "Msg?msg={msg}", arguments = listOf(
                navArgument("msg") {
                    type = NavType.StringType
                    defaultValue = "noMsg"
                })
            //deepLinks 추가

        ) {
            val msg = it.arguments?.getString("msg") ?: "noMsg"
            MsgShow(msg = msg)
        }
    }
}
