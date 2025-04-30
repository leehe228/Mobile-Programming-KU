package com.leehe228.week09.example04.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leehe228.week09.example04.model.Routes
import com.leehe228.week09.example04.uicomponents.LoginScreen
import com.leehe228.week09.example04.uicomponents.Register
import com.leehe228.week09.example04.uicomponents.WelcomeScreen

@Composable
fun LoginNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(route = Routes.Login.route) {
            LoginScreen(
                onWelcomeNavigate = { navController.navigate(Routes.Welcome.route) },
                onRegisterNavigate = { navController.navigate(Routes.Register.route) }
            )
        }

        composable(route = Routes.Welcome.route) {
            WelcomeScreen()
        }

        composable(route = Routes.Register.route) {
            Register()
        }
    }
}
