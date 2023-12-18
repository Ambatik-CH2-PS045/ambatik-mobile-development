package com.example.ambatik.ui.screen.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItemLeaderboard
import com.example.ambatik.api.response.DataItemQuiz
import com.example.ambatik.api.response.ResponseLeaderboard
import com.example.ambatik.api.response.ResponseListQuiz
import com.example.ambatik.data.repository.QuizRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class QuizViewModel(private val repository: QuizRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val quizList = MutableLiveData<List<DataItemQuiz?>?>()

    val leaderboardList = MutableLiveData<List<DataItemLeaderboard?>?>()
    val errorLeaderboard = MutableLiveData<String?>()
    val statusLeaderboard: MutableLiveData<Boolean> = MutableLiveData()

    fun getQuiz(id: Int){
        viewModelScope.launch {
            try {
                _loading.value = true
                val quizResponse = repository.getQuiz(id)
                status.postValue(true)
                quizList.postValue(quizResponse.data)
                Log.d("QUIZ", "$quizResponse")
            }catch (e: HttpException){
                _loading.value = false
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("QUIZ", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseListQuiz::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("QUIZ", "$e")
            }catch (e: Exception) {
                _loading.value = false
                error.postValue("Terjadi kesalahan saat memuat data")
                status.postValue(false)
                Log.d("QUIZ", "$e")
            }
        }
    }

    fun getLeaderboard(){
        viewModelScope.launch {
            try {
                val responseLeaderboard = repository.getLeaderboard()
                statusLeaderboard.postValue(true)
                leaderboardList.postValue(responseLeaderboard.data)
                Log.d("LEADERBOARD", "$responseLeaderboard")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("LEADERBOARD", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseLeaderboard::class.java)
                errorLeaderboard.postValue(errorBody.message)
                statusLeaderboard.postValue(false)
                Log.d("LEADERBOARD", "$e")
            }catch (e: Exception) {
                _loading.value = false
                errorLeaderboard.postValue("Terjadi kesalahan saat memuat data")
                statusLeaderboard.postValue(false)
                Log.d("LEADERBOARD", "$e")
            }
        }
    }
}