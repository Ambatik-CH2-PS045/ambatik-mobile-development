package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseListQuiz
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserPreference

class QuizRepository(private val apiService: ApiService, private val userPreference: UserPreference) {
    suspend fun getQuiz(id: Int): ResponseListQuiz{
        return apiService.getQuiz(id)
    }

    companion object {
        @Volatile
        private var instance: QuizRepository? = null

        fun getInstance(apiService: ApiService, userPreference: UserPreference): QuizRepository =
            instance ?: synchronized(this) {
                instance ?: QuizRepository(apiService, userPreference)
            }.also { instance = it }
    }
}