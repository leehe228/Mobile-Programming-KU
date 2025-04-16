package com.leehe228.eweek07a.example01.navGraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leehe228.eweek07a.example01.model.Routes
import com.leehe228.eweek07a.example01.uicomponents.HomeScreen
import com.leehe228.eweek07a.example01.uicomponents.Screen_A
import com.leehe228.eweek07a.example01.uicomponents.Screen_B
import com.leehe228.eweek07a.example01.uicomponents.Screen_C
import com.leehe228.eweek07a.example01.uicomponents.Screen_D

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) {
            HomeScreen(
                onNavigateA = { navController.navigate(Routes.ScreenA.route) },
                onNavigateB = { navController.navigate(Routes.ScreenB.route) }
            )
        }

        composable(route = Routes.ScreenA.route) {
            Screen_A(
                onNavigateC = { navController.navigate(Routes.ScreenC.route) },
                onNavigateD = { navController.navigate(Routes.ScreenD.route) }
            )
        }

        composable(route = Routes.ScreenB.route) {
            Screen_B()
        }

        composable(route = Routes.ScreenC.route) {
            Screen_C(onNavigate = { navController.navigate(Routes.Home.route) })
        }

        composable(route = Routes.ScreenD.route) {
            Screen_D()
        }
    }
}
