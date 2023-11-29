package com.example.ambatik.ui.screen.shopping

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItemShop
import com.example.ambatik.api.response.ResponseShop
import com.example.ambatik.data.repository.ShopRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ShoppingViewModel(private val repository: ShopRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val shopList = MutableLiveData<List<DataItemShop>>()

    fun getShop(){
        viewModelScope.launch {
            try {
                _loading.value = true
                val shopResponse = repository.getShop()
                status.postValue(true)
                shopList.postValue(shopResponse.data)
                Log.d("SHOP", "$shopResponse")
            }catch (e: HttpException){
                _loading.value = false
                val jsonInString = e.response()?.errorBody()?.string()
                Log.d("SHOP", "Error Response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseShop::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("SHOP", "$e")
            }catch (e: HttpException){
                _loading.value = false
                error.postValue("Terjadi kesalahan saat memuat data")
                status.postValue(false)
                Log.d("SHOP", "$e")
            }
        }
    }
}