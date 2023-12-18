package com.example.ambatik.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ambatik.ui.screen.home.HomeScreen
import com.example.ambatik.ui.screen.login.LoginScreen
import com.example.ambatik.ui.screen.register.RegisterScreen
import com.example.ambatik.ui.screen.welcome.AmbatikApp

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
    ){
        composable(Screen.Welcome.route){
            AmbatikApp(navController)
        }
        composable(Screen.Login.route){
            LoginScreen(navController)
        }
        composable(Screen.Register.route){
            RegisterScreen(navController)
        }
        composable(Screen.Home.route){
            HomeScreen()
        }
    }
}