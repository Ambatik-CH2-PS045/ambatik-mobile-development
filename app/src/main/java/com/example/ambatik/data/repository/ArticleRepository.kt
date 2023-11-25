package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserPreference

class ArticleRepository(private val apiService: ApiService, private val userPreference: UserPreference) {

    suspend fun getArticle(): ResponseArticle{
        return apiService.getArticle()
    }

    companion object{
        @Volatile
        private var instance: ArticleRepository? = null
        fun getInstance(apiService: ApiService, userPreference: UserPreference): ArticleRepository =
            instance ?: synchronized(this){
                instance ?: ArticleRepository(apiService, userPreference)
            }.also { instance = it }
    }
}