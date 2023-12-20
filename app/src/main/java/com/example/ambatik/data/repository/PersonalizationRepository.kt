package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponsePersonalizationBatik
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserPreference

class PersonalizationRepository(private val apiService: ApiService, private val userPreference: UserPreference) {
    suspend fun personalization(userAnswers: List<Int?>): ResponsePersonalizationBatik{
        val requestBody = HashMap<String, Any>()
        requestBody["userAnswers"] = userAnswers
        return apiService.personalization(requestBody)
    }

    companion object {
        @Volatile
        private var instance: PersonalizationRepository? = null

        fun getInstance(apiService: ApiService, userPreference: UserPreference): PersonalizationRepository =
            instance ?: synchronized(this) {
                instance ?: PersonalizationRepository(apiService, userPreference)
            }.also { instance = it }
    }
}