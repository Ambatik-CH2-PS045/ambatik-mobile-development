package com.example.ambatik.ui.screen.order

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItemOrder
import com.example.ambatik.api.response.ResponseGetOrder
import com.example.ambatik.data.repository.OrderRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OrderViewModel(private val repository: OrderRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val orderList = MutableLiveData<List<DataItemOrder?>?>()

    fun getOrder(id: Int){
        viewModelScope.launch {
            try {
                val orderResponse = repository.getOrder(id)
                orderList.postValue(orderResponse.data)
                status.postValue(true)
                Log.d("ORDER", "$orderResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("ORDER", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseGetOrder::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("ORDER", "$e")
            }catch (e: Exception) {
                error.postValue("Terjadi kesalahan saat membuat data")
                status.postValue(false)
                Log.d("ORDER", "$e")
            }
        }
    }
}