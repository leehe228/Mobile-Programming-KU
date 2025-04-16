package com.leehe228.eweek07a.example01.uicomponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.leehe228.eweek07a.example01.navGraph.NavGraph

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}
