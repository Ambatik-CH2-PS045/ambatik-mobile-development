package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseCheckout
import com.example.ambatik.api.response.ResponseDetailTransaksi
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
        val requestBody = HashMap<String, Any>()
        requestBody["totalqty"] = totalQty.toString()
        requestBody["grandtotal"] = grandTotal.toString()
        requestBody["userId"] = userId
        requestBody["eachqtys"] = eachQtys
        requestBody["eachprices"] = eachPrices
        requestBody["productIds"] = productIds
        return apiService.checkoutProduct(requestBody)

    }

    suspend fun getOrder(id: Int): ResponseGetOrder{
        return apiService.getOrder(id)
    }

    suspend fun getDetailOrder(idOrder: Int, idUser: Int): ResponseDetailTransaksi{
        return apiService.getDetailOrder(idOrder, idUser)
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