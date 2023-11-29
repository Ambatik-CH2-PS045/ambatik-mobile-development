package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseShop
import com.example.ambatik.api.response.ResponseShopDetail
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserPreference

class ShopRepository(private val apiService: ApiService, private val userPreference: UserPreference) {
    suspend fun getShop(): ResponseShop{
        return apiService.getShop()
    }

    suspend fun getDetailShop(id: Int): ResponseShopDetail{
        return apiService.getDetailShop(id)
    }

    companion object{
        @Volatile
        private var instance: ShopRepository? = null
        fun getInstance(apiService: ApiService, userPreference: UserPreference): ShopRepository =
            instance ?: synchronized(this){
                instance ?: ShopRepository(apiService, userPreference)
            }.also { instance = it }
    }
}