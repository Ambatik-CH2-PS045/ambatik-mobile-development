package com.example.ambatik.ui.screen.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.ResponseCheckout
import com.example.ambatik.data.repository.OrderRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AddOrderViewModel(private val repository: OrderRepository): ViewModel() {

    val errorCheckout = MutableLiveData<String>()
    fun checkout(
        totalQty: Int?,
        grandTotal: Int?,
        userId: Int,
        eachQtys: List<Int?>,
        eachPrices: List<Int?>,
        productIds: List<Int?>,
    ){
        viewModelScope.launch {
            try {
                val responseCheckout = repository.checkoutProduct(totalQty, grandTotal, userId, eachQtys, eachPrices, productIds)
                Log.d("CHECKOUT", "ORDER MASUK $responseCheckout, $totalQty,  $userId, $grandTotal, ${eachQtys.size}, ${eachPrices.size}, ${productIds.size}")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ResponseCheckout::class.java)
                val errorMassage = errorBody?.message ?: "Terjadi kesalahan saat checkout"
                errorCheckout.postValue(errorMassage)
                Log.d("CHECKOUT", "$e")
            }catch (e: HttpException){
                errorCheckout.postValue("Terjadi kesalahan saat memuat data")
                Log.d("CHECKOUT", "$e")
            }
        }
    }

}