package com.example.ambatik.api.retrofit

import android.content.Context
import com.example.ambatik.BuildConfig
import com.example.ambatik.data.pref.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        fun getApiService(pref: UserPreference, context: Context): ApiService{
            val authInterceptor = Interceptor {chain ->
                val req = chain.request()
                val token = runBlocking { pref.getSession().first() }
                val requestHeader = req.newBuilder()
                    .addHeader("Authorization", "Bearer ${token.token}")
                    .build()
                chain.proceed(requestHeader)
            }
            val loginInterceptor = if(BuildConfig.DEBUG) { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }else { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE) }
            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loginInterceptor)
                .build()
            val baseURL = BuildConfig.BASE_URL
            val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}