package com.example.ambatik.ui.screen.detailarticle

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItem
import com.example.ambatik.api.response.DataItemArticle
import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.data.repository.ArticleRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailArticleViewModel(private val repository: ArticleRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val detailArticle = MutableLiveData<DataItemArticle>()

    fun getDetailStory(id: Int, idUser: Int){
        viewModelScope.launch {
            try {
                val articleDetailResponse = repository.getDetailArticle(id, idUser)
                status.postValue(true)
                detailArticle.postValue(articleDetailResponse.data)
                Log.d("Article", "$articleDetailResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("Article", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseArticle::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("Article", "$e")
            }catch (e: Exception) {
                error.postValue("Terjadi kesalahan saat membuat data")
                status.postValue(false)
                Log.d("Article", "$e")
            }
        }
    }
}