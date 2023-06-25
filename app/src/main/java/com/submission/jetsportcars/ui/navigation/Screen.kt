package com.submission.jetsportcars.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object Detail: Screen("home/{carId}"){
        fun createRoute(carId: Long) = "home/$carId"
    }
}