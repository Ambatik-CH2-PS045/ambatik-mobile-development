package com.example.ambatik.api.retrofit

import com.example.ambatik.api.response.ResponseAddCart
import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.api.response.ResponseBatik
import com.example.ambatik.api.response.ResponseCart
import com.example.ambatik.api.response.ResponseCheckout
import com.example.ambatik.api.response.ResponseDetailArticle
import com.example.ambatik.api.response.ResponseDetailBatik
import com.example.ambatik.api.response.ResponseDetailuser
import com.example.ambatik.api.response.ResponseEditProfile
import com.example.ambatik.api.response.ResponseGetOrder
import com.example.ambatik.api.response.ResponseLikeArticle
import com.example.ambatik.api.response.ResponseListLikeArticle
import com.example.ambatik.api.response.ResponseListQuiz
import com.example.ambatik.api.response.ResponseLogin
import com.example.ambatik.api.response.ResponseQuizQuestion
import com.example.ambatik.api.response.ResponseRegister
import com.example.ambatik.api.response.ResponseShop
import com.example.ambatik.api.response.ResponseShopDetail
import com.example.ambatik.api.response.ResponseSubmitQuiz
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService{
    @POST("users/register")
    suspend fun register(
        @Body request: HashMap<String, String>
    ): ResponseRegister

    @POST("users/login")
    suspend fun login(
        @Body request: HashMap<String, String>
    ): ResponseLogin

    @GET("article")
    suspend fun getArticle(): ResponseArticle

    @GET("article/details/{id}/{idUser}")
    suspend fun getDetailArticle(
        @Path("id") id: Int,
        @Path("idUser") idUser: Int
    ): ResponseDetailArticle

    @GET("article/like/{idUser}")
    suspend fun getLikeArticle(
        @Path("idUser") idUser: Int
    ): ResponseListLikeArticle

    @POST("article/like")
    suspend fun likeArticle(
        @Body request: HashMap<String, String>
    ): ResponseLikeArticle

    @GET("product")
    suspend fun getShop(): ResponseShop

    @GET("product/details/{id}")
    suspend fun getDetailShop(
        @Path("id") id: Int
    ): ResponseShopDetail

    @POST("product/cart")
    suspend fun addToCart(
        @Body request: HashMap<String, String>
    ) : ResponseAddCart

    @GET("product/cart/{id}")
    suspend fun getCart(
        @Path("id") id: Int
    ): ResponseCart

    @GET("users/details/{idUser}")
    suspend fun getUserDetail(
        @Path("idUser") idUser: Int
    ): ResponseDetailuser

    @PATCH("users/update/{idUser}")
    suspend fun updateUserProfile(
        @Path("idUser") idUser: Int,
        @Body request: HashMap<String, String>
    ): ResponseEditProfile

    @POST("order/checkout")
    suspend fun checkoutProduct(
        @Body request: HashMap<String, Any>
    ): ResponseCheckout

    @GET("order/{idUser}")
    suspend fun getOrder(
        @Path("idUser") idUser: Int
    ): ResponseGetOrder

    @GET("quiz/list/{idUser}")
    suspend fun getQuiz(
        @Path("idUser") idUser: Int
    ): ResponseListQuiz

    @GET("quiz/{idModul}/question/{idQuestion}")
    suspend fun getQuestion(
        @Path("idModul") idUser: Int,
        @Path("idQuestion") idQuestion: Int
    ): ResponseQuizQuestion

    @POST("quiz/submit")
    suspend fun submitQuiz(
        @Body request: HashMap<String, Any>
    ): ResponseSubmitQuiz

    @GET("batik")
    suspend fun getBatik(): ResponseBatik

    @GET("batik/{idBatik}")
    suspend fun getDetailBatik(
        @Path("idBatik") idBatik: Int
    ): ResponseDetailBatik
}