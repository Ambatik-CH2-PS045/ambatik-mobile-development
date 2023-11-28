package com.example.ambatik.api.retrofit

import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.api.response.ResponseDetailArticle
import com.example.ambatik.api.response.ResponseLikeArticle
import com.example.ambatik.api.response.ResponseLogin
import com.example.ambatik.api.response.ResponseRegister
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
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

    @POST("article/like")
    suspend fun likeArticle(
        @Body request: HashMap<String, String>
    ): ResponseLikeArticle
}