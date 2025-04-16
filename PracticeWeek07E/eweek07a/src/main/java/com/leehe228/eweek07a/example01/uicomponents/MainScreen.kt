package com.leehe228.eweek07a.example01.uicomponents

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.leehe228.eweek07a.example01.navGraph.NavGraph

@SuppressLint("RestrictedApi")
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    navController.addOnDestinationChangedListener { _, _, _ ->
        navController.currentBackStack.value.forEachIndexed { index, entry ->
            Log.d("backstack", "$index: ${entry.destination.route}")
        }
    }
    NavGraph(navController = navController)
}
