package com.example.ambatik.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ambatik.ui.screen.articel.ArticelScreen
import com.example.ambatik.ui.screen.articlelike.ArticleLikeScreen
import com.example.ambatik.ui.screen.cart.CartScreen
import com.example.ambatik.ui.screen.detailarticle.DetailArticleScreen
import com.example.ambatik.ui.screen.detailbatik.DetailBatikScreen
import com.example.ambatik.ui.screen.detailorder.DetailOrderScreen
import com.example.ambatik.ui.screen.detailshopping.DetailShopScreen
import com.example.ambatik.ui.screen.editprofile.EditProfileScreen
import com.example.ambatik.ui.screen.home.HomeScreen
import com.example.ambatik.ui.screen.login.LoginScreen
import com.example.ambatik.ui.screen.order.OrderScreen
import com.example.ambatik.ui.screen.personalization.PersonalizationScreen
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
                },
                navigateToDetailBatik = {batikId ->
                    navController.navigate(Screen.DetailBatik.createRoute(batikId))
                }
            )
        }
        composable(Screen.Quiz.route){
            QuizScreen(
                navController,
                navigateToStartQuiz = {typeQuestion ->
                    navController.navigate(Screen.StartQuiz.createRoute(typeQuestion))
                },
                navigateToWelcome = {
                    navController.navigate(Screen.Welcome.route)
                }
            )
        }
        composable(Screen.Shopping.route){
            ShoppingScreen(
                navController,
                navigateToDetailShop = {shopId ->
                    navController.navigate(Screen.DetailShop.createRoute(shopId))
                },
                navigateToWelcome = {
                    navController.navigate(Screen.Welcome.route)
                }
            )
        }
        composable(Screen.Profile.route){
            ProfileScreen(
                navController,
                navigateToWelcome = {
                    navController.navigate(Screen.Welcome.route)
                }
            )
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
            ScanScreen(
                navigateToWelcome = {
                    navController.navigate(Screen.Welcome.route)
                }
            )
        }
        val articleId = "articleId"
        composable(
            route = Screen.DetailArticle.route,
            arguments = listOf(navArgument(articleId){ type = NavType.IntType})
            ){
            val id = it.arguments?.getInt(articleId) ?: 0
            DetailArticleScreen(
                articleId = id,
                navigateToWelcome = {
                    navController.navigate(Screen.Welcome.route)
                }
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
                shopId = id,
                navigateToWelcome = {
                    navController.navigate(Screen.Welcome.route)
                }
            )
        }
        composable(Screen.Cart.route){
            CartScreen(
                navController,
                navigateToOrder = {
                    navController.navigate(Screen.Order.route)
                }
            )
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
            OrderScreen(
                navController,
                navigateToDetailOrder = {orderId ->
                    navController.navigate(Screen.DetailOrder.createRoute(orderId))
                }
            )
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
        val idBatik = "idBatik"
        composable(
            route = Screen.DetailBatik.route,
            arguments = listOf(navArgument(idBatik){type = NavType.IntType})
        ){
            val id = it.arguments?.getInt(idBatik) ?: 0
            DetailBatikScreen(
                idBatik = id,
                navigateToDetailProduct = {productId ->
                    navController.navigate(Screen.DetailShop.createRoute(productId))
                }
            )
        }
        val idOrder = "idOrder"
        composable(
            route = Screen.DetailOrder.route,
            arguments = listOf(navArgument(idOrder){type = NavType.IntType})
        ){
            val id = it.arguments?.getInt(idOrder) ?: 0
            DetailOrderScreen(
                idOrder = id
            )
        }
        composable(Screen.Personalisasi.route){
            PersonalizationScreen(
                navigateToDetailBatik = {batikId ->
                    navController.navigate(Screen.DetailBatik.createRoute(batikId))}
            )
        }
    }
}