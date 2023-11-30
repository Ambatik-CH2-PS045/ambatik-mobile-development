package com.example.ambatik.ui.screen.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItemCart
import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.api.response.ResponseCart
import com.example.ambatik.data.repository.ArticleRepository
import com.example.ambatik.data.repository.CartRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CartViewModel(private val repository: CartRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val dataCart = MutableLiveData<ResponseCart>()
    val listCart = MutableLiveData<List<DataItemCart?>?>()

    fun getCart(id: Int){
        viewModelScope.launch {
            try {
                val cartResponse = repository.getCart(id)
                status.postValue(true)
                dataCart.postValue(cartResponse)
                listCart.postValue(cartResponse.data)
                Log.d("CART", "$cartResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("CART", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseCart::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("CART", "$e")
            }catch (e: HttpException){
                error.postValue("Terjadi kesalahan saat memuat data")
                status.postValue(false)
                Log.d("CART", "$e")
            }
        }
    }
}