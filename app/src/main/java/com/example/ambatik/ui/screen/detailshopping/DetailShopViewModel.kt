package com.example.ambatik.ui.screen.detailshopping

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItemShopDetail
import com.example.ambatik.api.response.ResponseArticle
import com.example.ambatik.api.response.ResponseShopDetail
import com.example.ambatik.data.repository.ShopRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailShopViewModel(private val repository: ShopRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val detailShop = MutableLiveData<DataItemShopDetail?>()

    fun getDetailShop(id: Int){
        viewModelScope.launch {
            try {
                val shopDetailResponse = repository.getDetailShop(id)
                status.postValue(true)
                detailShop.postValue(shopDetailResponse.data)
                Log.e("DETAIL SHOP", "$shopDetailResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("DETAIL SHOP", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseShopDetail::class.java)
                error.postValue(errorBody.message)
                status.postValue(false)
                Log.d("DETAIL SHOP", "$e")
            }catch (e: HttpException){
                error.postValue("Terjadi kesalahan saat membuat data")
                status.postValue(false)
                Log.d("ARTICLE", "$e")
            }
        }
    }
}