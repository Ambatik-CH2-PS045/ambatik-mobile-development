package com.example.ambatik.ui.screen.articel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItem
import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.data.repository.ArticleRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ArticleViewModel(private val repository: ArticleRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val articleList = MutableLiveData<List<DataItem>>()

    fun getStory(){
        viewModelScope.launch {
            try {
                _loading.value = true
                val articleResponse = repository.getArticle()
                status.postValue(true)
                articleList.postValue(articleResponse.data)
                Log.d("Article", "$articleResponse")
            }catch (e: HttpException){
                _loading.value = false
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("Article", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseArticle::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("Article", "$e")
            }catch (e: Exception) {
                _loading.value = false
                error.postValue("Terjadi kesalahan saat membuat data")
                status.postValue(false)
                Log.d("Article", "$e")
            }
        }
    }
}