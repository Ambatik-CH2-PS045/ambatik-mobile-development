package com.example.ambatik.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ambatik.ui.screen.articel.ArticelScreen
import com.example.ambatik.ui.screen.profile.ProfileScreen
import com.example.ambatik.ui.screen.quiz.QuizScreen
import com.example.ambatik.ui.screen.scan.ScanScreen
import com.example.ambatik.ui.screen.shopping.ShoppingScreen
import com.example.ambatik.ui.screen.welcome.AmbatikApp

@Composable
fun NavigationBottomBar(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = ScreenHomePage.Articel.route,
        modifier = Modifier.padding(innerPadding)
    ){
        composable(ScreenHomePage.Articel.route){
            ArticelScreen()
        }
        composable(ScreenHomePage.Quiz.route){
            QuizScreen()
        }
        composable(ScreenHomePage.Scan.route){
            ScanScreen()
        }
        composable(ScreenHomePage.Shopping.route){
            ShoppingScreen()
        }
        composable(ScreenHomePage.Profile.route){
            ProfileScreen(navController)
        }
    }
}