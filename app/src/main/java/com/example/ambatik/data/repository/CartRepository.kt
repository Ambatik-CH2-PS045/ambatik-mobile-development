package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseAddCart
import com.example.ambatik.api.response.ResponseCart
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserPreference

class CartRepository(private val apiService: ApiService, private val userPreference: UserPreference) {
    suspend fun addToCart(userId: Int, productId: Int, command: String): ResponseAddCart{
        val requestBody = HashMap<String, String>()
        requestBody["userId"] = userId.toString()
        requestBody["productId"] = productId.toString()
        requestBody["command"] = command
        return apiService.addToCart(requestBody)
    }

    suspend fun getCart(id: Int): ResponseCart{
        return apiService.getCart(id)
    }

    companion object{
        @Volatile
        private var instance: CartRepository? = null
        fun getInstance(apiService: ApiService, userPreference: UserPreference): CartRepository =
            instance ?: synchronized(this){
                instance ?: CartRepository(apiService, userPreference)
            }.also { instance = it }
    }
}