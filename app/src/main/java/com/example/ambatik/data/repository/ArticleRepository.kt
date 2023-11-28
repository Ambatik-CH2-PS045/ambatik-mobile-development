package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.api.response.ResponseDetailArticle
import com.example.ambatik.api.response.ResponseLikeArticle
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserPreference

class ArticleRepository(private val apiService: ApiService, private val userPreference: UserPreference) {

    suspend fun getArticle(): ResponseArticle{
        return apiService.getArticle()
    }

    suspend fun getDetailArticle(id: Int, idUser: Int): ResponseDetailArticle{
        return apiService.getDetailArticle(id, idUser)
    }

    suspend fun likeArticle(userId: Int, articleId: Int): ResponseLikeArticle{
        val requestBody = HashMap<String, String>()
        requestBody["userId"] = userId.toString()
        requestBody["articleId"] = articleId.toString()
        return apiService.likeArticle(requestBody)
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