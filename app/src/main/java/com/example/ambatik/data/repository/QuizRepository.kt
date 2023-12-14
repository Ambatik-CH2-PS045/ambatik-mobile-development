package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseListQuiz
import com.example.ambatik.api.response.ResponseQuizQuestion
import com.example.ambatik.api.response.ResponseSubmitQuiz
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserPreference

class QuizRepository(private val apiService: ApiService, private val userPreference: UserPreference) {
    suspend fun getQuiz(id: Int): ResponseListQuiz{
        return apiService.getQuiz(id)
    }

    suspend fun getQuestion(idModul: Int, idQuestion: Int): ResponseQuizQuestion{
        return apiService.getQuestion(idModul, idQuestion)
    }

    suspend fun submitQuiz(
        userId: Int?,
        quizId: Int?,
        questionIds: List<Int?>,
        answerIds: List<Int?>
    ): ResponseSubmitQuiz{
        val requestBody = HashMap<String, Any>()
        if (userId != null) {
            requestBody["userid"] = userId.toInt()
        }
        if (quizId != null) {
            requestBody["quizId"] = quizId.toInt()
        }
        requestBody["questionIds"] = questionIds
        requestBody["answerIds"] = answerIds
        return apiService.submitQuiz(requestBody)
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