package com.example.ambatik.ui.screen.detailshopping

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.ResponseAddCart
import com.example.ambatik.data.repository.CartRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AddToCartViewModel(private val repository: CartRepository): ViewModel() {
    val errorAddToCart = MutableLiveData<String?>()

    fun addToCart(idUser: Int, productId: Int, command: String){
        viewModelScope.launch {
            try {
                val addToCartResponse = repository.addToCart(idUser, productId, command)
                Log.d("ADD TO CART", "$addToCartResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ResponseAddCart::class.java)
                val errorMassage = errorBody?.message ?: "Terjadi kesalahan saat menambah cart"
                errorAddToCart.postValue(errorMassage)
                Log.d("ADD TO CART", "$e")
            }catch (e: HttpException){
                errorAddToCart.postValue("Terjadi kesalahan saat menambah cart")
                Log.d("ADD TO CART", "$e")
            }
        }
    }
}