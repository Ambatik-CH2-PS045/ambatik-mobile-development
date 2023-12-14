package com.example.ambatik.ui.screen.startquiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.AnswersItem
import com.example.ambatik.api.response.DataItemSummary
import com.example.ambatik.api.response.DataQuestion
import com.example.ambatik.api.response.ResponseCheckout
import com.example.ambatik.api.response.ResponseQuizQuestion
import com.example.ambatik.data.repository.QuizRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StartQuizViewModel(private val repository: QuizRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val quizQuestion = MutableLiveData<DataQuestion?>()
    val quizAnswer = MutableLiveData<List<AnswersItem?>?>()

    val submitQuizez = MutableLiveData<List<DataItemSummary?>?>()
    private val _statusSubmitQuiz: MutableLiveData<Boolean> = MutableLiveData()
    val statusSubmitQuiz: LiveData<Boolean> = _statusSubmitQuiz
    val errorSubmit = MutableLiveData<String>()

    fun getQuestion(idModul: Int, idQuestion: Int){
        viewModelScope.launch {
            try {
                val questionResponse = repository.getQuestion(idModul, idQuestion)
                status.postValue(true)
                quizQuestion.postValue(questionResponse.data)
                quizAnswer.postValue(questionResponse.data?.answers)
                Log.d("QUESTION", "$questionResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("QUESTION", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseQuizQuestion::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("QUESTION", "$e")
            }catch (e: Exception) {
                error.postValue("Terjadi kesalahan saat memuat data")
                status.postValue(false)
                Log.d("QUESTION", "$e")
            }
        }
    }

    fun submitQuiz(
        userId: Int?,
        quizId: Int?,
        questionIds: List<Int?>,
        answerIds: List<Int?>
    ){
        viewModelScope.launch {
            try {
                val responseSubmitQuiz = repository.submitQuiz(userId, quizId, questionIds, answerIds)
                submitQuizez.postValue(listOf(responseSubmitQuiz.data?.get(0)))
                Log.d("SUBMIT QUIZ", "submitQuiz: $responseSubmitQuiz, $submitQuizez")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ResponseCheckout::class.java)
                val errorMassage = errorBody?.message ?: "Terjadi kesalahan saat Submit Quiz"
                _statusSubmitQuiz.value = false
                errorSubmit.postValue(errorMassage)
                Log.d("SUBMIT QUIZ", "$e")
            }catch (e: HttpException){
                _statusSubmitQuiz.value = false
                errorSubmit.postValue("Terjadi kesalahan saat memuat data")
                Log.d("SUBMIT QUIZ", "$e")
            }
        }
    }
}