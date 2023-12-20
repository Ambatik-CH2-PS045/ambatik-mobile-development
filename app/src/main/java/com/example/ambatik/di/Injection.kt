package com.example.ambatik.di

import android.content.Context
import android.util.Log
import com.example.ambatik.api.retrofit.ApiConfig
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.data.repository.ArticleRepository
import com.example.ambatik.data.repository.BatikRepository
import com.example.ambatik.data.repository.CartRepository
import com.example.ambatik.data.repository.OrderRepository
import com.example.ambatik.data.repository.QuizRepository
import com.example.ambatik.data.repository.ShopRepository
import com.example.ambatik.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context): UserRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token, context)
        val apiService = ApiConfig.getApiService(pref, context)
        return UserRepository.getInstance(apiService, pref)
    }

    fun provideArticleRepository(context: Context): ArticleRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token, context)
        val apiService = ApiConfig.getApiService(pref, context)
        Log.d("CHECK TOKEN", "INJECTION: ${user.token}")
        return ArticleRepository.getInstance(apiService, pref)
    }

    fun provideShopRepository(context: Context): ShopRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token, context)
        val apiService = ApiConfig.getApiService(pref, context)
        return ShopRepository.getInstance(apiService, pref)
    }

    fun provideCartRepository(context: Context): CartRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token, context)
        val apiService = ApiConfig.getApiService(pref, context)
        return CartRepository.getInstance(apiService, pref)
    }
    fun provideOrderRepository(context: Context): OrderRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token, context)
        val apiService = ApiConfig.getApiService(pref, context)
        return OrderRepository.getInstance(apiService, pref)
    }

    fun provideQuizRepository(context: Context): QuizRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token, context)
        val apiService = ApiConfig.getApiService(pref, context)
        return QuizRepository.getInstance(apiService, pref)
    }

    fun provideBatikRepository(context: Context): BatikRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token, context)
        val apiService = ApiConfig.getApiService(pref, context)
        return BatikRepository.getInstance(apiService, pref)
    }
}