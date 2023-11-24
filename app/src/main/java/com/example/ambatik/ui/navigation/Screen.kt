package com.example.ambatik.ui.navigation

sealed class ScreenLandingPage(val route: String) {
    object Welcome: ScreenLandingPage("welcome")
    object Login: ScreenLandingPage("login")
    object Register: ScreenLandingPage("register")
    object Home: ScreenHomePage("home")
}
sealed class ScreenHomePage(val route: String){
    object Profile: ScreenHomePage("profile")
    object Quiz: ScreenHomePage("quiz")
    object Scan: ScreenHomePage("scan")
    object Shopping: ScreenHomePage("shopping")
    object Articel: ScreenHomePage("articel")
}

