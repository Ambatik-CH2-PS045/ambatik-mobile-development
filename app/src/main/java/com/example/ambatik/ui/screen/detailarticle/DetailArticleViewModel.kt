package com.example.ambatik.ui.screen.detailarticle

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItem
import com.example.ambatik.api.response.DataItemArticle
import com.example.ambatik.api.response.LikeItem
import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.api.response.ResponseLikeArticle
import com.example.ambatik.data.repository.ArticleRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailArticleViewModel(private val repository: ArticleRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val detailArticle = MutableLiveData<DataItemArticle>()
    val statusLike: MutableLiveData<Boolean> = MutableLiveData()

    fun getDetailStory(id: Int, idUser: Int){
        viewModelScope.launch {
            try {
                val articleDetailResponse = repository.getDetailArticle(id, idUser)
                status.postValue(true)
                statusLike.postValue(articleDetailResponse.liked)
                detailArticle.postValue(articleDetailResponse.data)
                Log.e("ARTICLE", "$articleDetailResponse")
                Log.d("ARTICLE LIKES", "${articleDetailResponse.data.likes[0].statusLike}")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("ARTICLE", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseArticle::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("ARTICLE", "$e")
            }catch (e: Exception) {
                error.postValue("Terjadi kesalahan saat membuat data")
                status.postValue(false)
                Log.d("ARTICLE", "$e")
            }
        }
    }

    fun likeArticle(idUser: Int, articleId: Int){
        viewModelScope.launch {
            try {
                val likeResponse = repository.likeArticle(idUser, articleId)
                Log.d("LIKE ARTICLE", "$likeResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ResponseLikeArticle::class.java)
                val errorMessage = errorBody?.message ?: "Terjadi kesalahan saat like"
                error.postValue(errorMessage)
                Log.d("LIKE ARTICLE", "$e")
            }catch (e: Exception){
                error.postValue("Terjadi kesalahan saat like")
                Log.d("LIKE ARTICLE", "$e")
            }
        }
    }
}