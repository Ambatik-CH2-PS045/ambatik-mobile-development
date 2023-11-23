package com.example.ambatik.di

import android.content.Context
import com.example.ambatik.api.retrofit.ApiConfig
import com.example.ambatik.data.repository.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository{
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}