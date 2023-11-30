package com.example.ambatik.di

import android.content.Context
import com.example.ambatik.api.retrofit.ApiConfig
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.data.repository.ArticleRepository
import com.example.ambatik.data.repository.CartRepository
import com.example.ambatik.data.repository.ShopRepository
import com.example.ambatik.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context): UserRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(apiService, pref)
    }

    fun provideArticleRepository(context: Context): ArticleRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return ArticleRepository.getInstance(apiService, pref)
    }

    fun provideShopRepository(context: Context): ShopRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return ShopRepository.getInstance(apiService, pref)
    }

    fun provideCartRepository(context: Context): CartRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return CartRepository.getInstance(apiService, pref)
    }
}