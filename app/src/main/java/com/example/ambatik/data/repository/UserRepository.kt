package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseLogin
import com.example.ambatik.api.response.ResponseRegister
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository(private val apiService: ApiService, private val userPreference: UserPreference) {
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

    suspend fun saveSession(user: UserModel){
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object{
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService, userPreference: UserPreference): UserRepository =
            instance ?: synchronized(this){
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}