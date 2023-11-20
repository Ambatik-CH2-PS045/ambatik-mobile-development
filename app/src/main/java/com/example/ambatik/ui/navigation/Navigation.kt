package com.example.ambatik.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ambatik.AmbatikApp
import com.example.ambatik.ui.screen.login.LoginScreen
import com.example.ambatik.ui.screen.register.RegisterScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Welcome.route){
        composable(Screen.Welcome.route){
            AmbatikApp(navController = navController)
        }
        composable(Screen.Login.route){
            LoginScreen()
        }
        composable(Screen.Register.route){
            RegisterScreen()
        }
    }
}