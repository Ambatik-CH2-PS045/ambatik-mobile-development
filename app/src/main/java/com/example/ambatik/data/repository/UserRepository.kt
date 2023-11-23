package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseLogin
import com.example.ambatik.api.response.ResponseRegister
import com.example.ambatik.api.retrofit.ApiService

class UserRepository(private val apiService: ApiService) {
    suspend fun register(name: String, email: String, username: String, password: String, phone: String): ResponseRegister{
        val requestBody = HashMap<String, String>()
        requestBody["name"] = name
        requestBody["email"] = email
        requestBody["username"] = username
        requestBody["password"] = password
        requestBody["phone"] = phone
        return apiService.register(requestBody)
    }

    suspend fun login(username: String, password: String): ResponseLogin{
        val requestBody = HashMap<String, String>()
        requestBody["username"] = username
        requestBody["password"] = password
        return apiService.login(requestBody)
    }

    companion object{
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService): UserRepository =
            instance ?: synchronized(this){
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}