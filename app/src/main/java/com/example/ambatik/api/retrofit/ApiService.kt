package com.example.ambatik.api.retrofit

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService{
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("phone") phone: String,
    )
}