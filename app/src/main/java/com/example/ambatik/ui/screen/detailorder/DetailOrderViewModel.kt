package com.example.ambatik.ui.screen.detailorder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItemDetailOrder
import com.example.ambatik.api.response.ProductsItem
import com.example.ambatik.api.response.ResponseCheckout
import com.example.ambatik.api.response.ResponseDetailTransaksi
import com.example.ambatik.data.repository.OrderRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailOrderViewModel(private val repository: OrderRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val detailOrder = MutableLiveData<List<DataItemDetailOrder?>?>()
    val listOrder = MutableLiveData<List<ProductsItem?>?>()

    fun getDetailOrder(idOrder: Int, idUser: Int){
        viewModelScope.launch {
            try {
                val detailOrderResponse = repository.getDetailOrder(idOrder, idUser)
                status.postValue(true)
                detailOrder.postValue(detailOrderResponse.data)
                listOrder.postValue(detailOrderResponse.data?.firstOrNull()?.products)
                Log.d("DETAIL ORDER", "$detailOrderResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ResponseDetailTransaksi::class.java)
                val errorMassage = errorBody?.message ?: "Terjadi kesalahan saat memuat data"
                error.postValue(errorMassage)
                Log.d("SUBMIT QUIZ", "$e")
            }catch (e: HttpException){
                error.postValue("Terjadi kesalahan saat memuat data")
                Log.d("SUBMIT QUIZ", "$e")
            }
        }
    }
}