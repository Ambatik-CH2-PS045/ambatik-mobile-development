package com.example.ambatik.ui.navigation

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome")
    object Login: Screen("login")
    object Register: Screen("register")
    object Home: Screen("home")
    object Profile: Screen("profile")
    object Quiz: Screen("quiz")
    object Scan: Screen("scan")
    object Shopping: Screen("shopping")
    object Articel: Screen("articel")
    object DetailArticle: Screen("articel/{articleId}"){
        fun createRoute(articleId: Int) = "articel/$articleId"
    }
    object DetailShop: Screen("shopping/{shoppingId}"){
        fun createRoute(shoppingId: Int) = "shopping/$shoppingId"
    }
    object Cart: Screen("product/cart")
    object LikeArticle: Screen("article/like")
    object EditProfile: Screen("users/details")
    object Order: Screen("order")
    object StartQuiz: Screen("quiz/{typeQuestion}"){
        fun createRoute(typeQuestion: String) = "quiz/$typeQuestion"
    }
    object DetailBatik: Screen("batik/{idBatik}"){
        fun createRoute(idBatik: Int) = "batik/$idBatik"
    }
    object DetailOrder: Screen("order/{idOrder}"){
        fun createRoute(orderId: Int) = "order/$orderId"
    }
    object Personalisasi: Screen("personalisasi")
}