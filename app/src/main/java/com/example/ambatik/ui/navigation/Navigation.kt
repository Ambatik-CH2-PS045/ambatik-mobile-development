package com.example.ambatik.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ambatik.ui.screen.articel.ArticelScreen
import com.example.ambatik.ui.screen.welcome.AmbatikApp
import com.example.ambatik.ui.screen.home.HomeScreen
import com.example.ambatik.ui.screen.login.LoginScreen
import com.example.ambatik.ui.screen.profile.ProfileScreen
import com.example.ambatik.ui.screen.quiz.QuizScreen
import com.example.ambatik.ui.screen.register.RegisterScreen
import com.example.ambatik.ui.screen.scan.ScanScreen
import com.example.ambatik.ui.screen.shopping.ShoppingScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
    ){
        composable(Screen.Welcome.route){
            AmbatikApp(
                navController,
                navigateToHome = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
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
//        composable(Screen.Articel.route){
//            ArticelScreen(navController)
//        }
//        composable(Screen.Quiz.route){
//            QuizScreen(navController)
//        }
//        composable(Screen.Scan.route){
//            ScanScreen(navController)
//        }
//        composable(Screen.Shopping.route){
//            ShoppingScreen(navController)
//        }
//        composable(Screen.Profile.route){
//            ProfileScreen(navController)
//        }
    }
}