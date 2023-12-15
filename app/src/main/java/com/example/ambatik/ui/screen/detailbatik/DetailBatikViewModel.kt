package com.example.ambatik.ui.screen.detailbatik

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataDetailBatik
import com.example.ambatik.api.response.ResponseDetailBatik
import com.example.ambatik.data.repository.BatikRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailBatikViewModel(private val repository: BatikRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val detailBatik = MutableLiveData<DataDetailBatik?>()

    fun getDetailBatik(idBatik: Int){
        viewModelScope.launch {
            try {
                val batikDetailResponse = repository.getDetailBatik(idBatik)
                status.postValue(true)
                detailBatik.postValue(batikDetailResponse.data)
                Log.d("DETAIL BATIK", "$batikDetailResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("DETAIL BATIK", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseDetailBatik::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("DETAIL BATIK", "$e")
            }catch (e: Exception) {
                error.postValue("Terjadi kesalahan saat memuat data")
                status.postValue(false)
                Log.d("DETAIL BATIK", "$e")
            }
        }
    }
}