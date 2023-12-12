package com.example.ambatik.data.repository

import android.util.Log
import com.example.ambatik.api.response.ResponseCheckout
import com.example.ambatik.api.response.ResponseGetOrder
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserPreference

class OrderRepository(private val apiService: ApiService, private val userPreference: UserPreference){
    suspend fun checkoutProduct(
        totalQty: Int?,
        grandTotal: Int?,
        userId: Int,
        eachQtys: List<Int?>,
        eachPrices: List<Int?>,
        productIds: List<Int?>
    ): ResponseCheckout {
        val requestBody = HashMap<String, String>()
        requestBody["totalqty"] = totalQty.toString()
        requestBody["grandtotal"] = grandTotal.toString()
        requestBody["userId"] = userId.toString()
        requestBody["eachqtys"] = eachQtys.toString()
        requestBody["eachprices"] = eachPrices.toString()
        requestBody["productIds"] = productIds.toString()
        return apiService.checkoutProduct(requestBody)

    }


    suspend fun getOrder(id: Int): ResponseGetOrder{
        return apiService.getOrder(id)
    }

    companion object {
        @Volatile
        private var instance: OrderRepository? = null

        fun getInstance(apiService: ApiService, userPreference: UserPreference): OrderRepository =
            instance ?: synchronized(this) {
                instance ?: OrderRepository(apiService, userPreference)
            }.also { instance = it }
    }
}