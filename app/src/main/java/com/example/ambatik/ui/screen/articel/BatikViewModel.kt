package com.example.ambatik.ui.screen.articel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItem
import com.example.ambatik.api.response.DataItemBatik
import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.api.response.ResponseBatik
import com.example.ambatik.data.repository.BatikRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BatikViewModel(private val repository: BatikRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val batikList = MutableLiveData<List<DataItemBatik?>?>()

    fun getBatik(){
        viewModelScope.launch {
            try {
                val batikResponse = repository.getBatik()
                batikList.postValue(batikResponse.data)
                status.postValue(true)
                Log.d("BATIK", "$batikResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("BATIK", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseBatik::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("BATIK", "$e")
            }catch (e: Exception) {
                error.postValue("Terjadi kesalahan saat memuat data")
                status.postValue(false)
                Log.d("BATIK", "$e")
            }
        }
    }
}