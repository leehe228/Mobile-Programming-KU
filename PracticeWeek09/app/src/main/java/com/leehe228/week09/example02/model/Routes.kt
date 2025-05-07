package com.leehe228.week09.example02.model

sealed class Routes(val route: String) {
    object Home : Routes("Home")
    object Contacts : Routes("Contacts")
    object Favorites : Routes("Favorites")
}
