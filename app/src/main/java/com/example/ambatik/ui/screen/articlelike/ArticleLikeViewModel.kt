package com.example.ambatik.ui.screen.articlelike

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItemLike
import com.example.ambatik.api.response.ResponseListLikeArticle
import com.example.ambatik.data.repository.ArticleRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ArticleLikeViewModel(val repository: ArticleRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val likeArticleList = MutableLiveData<List<DataItemLike?>?>()

    fun getLikeArticleList(id: Int){
        viewModelScope.launch {
            try {
                _loading.value = true
                val listLikeArticleResponse = repository.getListLikeArticle(id)
                status.postValue(true)
                likeArticleList.postValue(listLikeArticleResponse.data)
                Log.d("List Like Article", "$listLikeArticleResponse")
            }catch (e: HttpException){
                _loading.value = false
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("List Like Article", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseListLikeArticle::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("List Like Article", "$e")
            }catch (e: HttpException){
                _loading.value = false
                error.postValue("Terjadi kesalahan saat memuat data")
                status.postValue(false)
                Log.d("List Like Article", "$e")
            }
        }
    }
}