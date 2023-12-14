package com.example.ambatik.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ambatik.ui.screen.articel.ArticelScreen
import com.example.ambatik.ui.screen.articlelike.ArticleLikeScreen
import com.example.ambatik.ui.screen.cart.CartScreen
import com.example.ambatik.ui.screen.detailarticle.DetailArticleScreen
import com.example.ambatik.ui.screen.detailshopping.DetailShopScreen
import com.example.ambatik.ui.screen.editprofile.EditProfileScreen
import com.example.ambatik.ui.screen.home.HomeScreen
import com.example.ambatik.ui.screen.login.LoginScreen
import com.example.ambatik.ui.screen.order.OrderScreen
import com.example.ambatik.ui.screen.profile.ProfileScreen
import com.example.ambatik.ui.screen.quiz.QuizScreen
import com.example.ambatik.ui.screen.register.RegisterScreen
import com.example.ambatik.ui.screen.scan.ScanScreen
import com.example.ambatik.ui.screen.shopping.ShoppingScreen
import com.example.ambatik.ui.screen.startquiz.StartQuizScreen
import com.example.ambatik.ui.screen.welcome.AmbatikApp

@Composable
fun NavigationBottom(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Articel.route,
        modifier = Modifier
            .padding(innerPadding)
    ){
        composable(Screen.Articel.route){
            ArticelScreen(
                navController,
                navigateToDetail = {articleId ->
                    navController.navigate(Screen.DetailArticle.createRoute(articleId))
                }
            )
        }
        composable(Screen.Quiz.route){
            QuizScreen(
                navController,
                navigateToStartQuiz = {typeQuestion ->
                    navController.navigate(Screen.StartQuiz.createRoute(typeQuestion))
                }
            )
        }
        composable(Screen.Shopping.route){
            ShoppingScreen(
                navController,
                navigateToDetailShop = {shopId ->
                    navController.navigate(Screen.DetailShop.createRoute(shopId))
                }
            )
        }
        composable(Screen.Profile.route){
            ProfileScreen(navController)
        }
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
        composable(Screen.Scan.route){
            ScanScreen()
        }
        val articleId = "articleId"
        composable(
            route = Screen.DetailArticle.route,
            arguments = listOf(navArgument(articleId){ type = NavType.IntType})
            ){
            val id = it.arguments?.getInt(articleId) ?: 0
            DetailArticleScreen(
                articleId = id
            )
        }
        val shopId = "shoppingId"
        composable(
            route = Screen.DetailShop.route,
            arguments = listOf(navArgument(shopId){ type = NavType.IntType })
        ){
            val id = it.arguments?.getInt(shopId) ?: 0
            DetailShopScreen(
                navController,
                shopId = id
            )
        }
        composable(Screen.Cart.route){
            CartScreen(navController)
        }
        composable(Screen.LikeArticle.route){
            ArticleLikeScreen(navigateToDetail = {articleId ->
                navController.navigate(Screen.DetailArticle.createRoute(articleId))
            })
        }
        composable(Screen.EditProfile.route){
            EditProfileScreen(navController)
        }
        composable(Screen.Order.route){
            OrderScreen(navController)
        }
        val typeQuestion = "typeQuestion"
        composable(
            route = Screen.StartQuiz.route,
            arguments = listOf(navArgument(typeQuestion){type = NavType.StringType})
        ){
            val type = it.arguments?.getString(typeQuestion) ?: ""
            StartQuizScreen(
                navController,
                quizType = type,
            )
        }
    }
}