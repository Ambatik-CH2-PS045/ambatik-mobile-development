package com.example.ambatik.data.repository

import android.net.Uri
import com.example.ambatik.api.response.ResponseDetailuser
import com.example.ambatik.api.response.ResponseEditProfile
import com.example.ambatik.api.response.ResponseLogin
import com.example.ambatik.api.response.ResponseRegister
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Multipart
import java.io.File

class UserRepository(private val apiService: ApiService, private val userPreference: UserPreference) {
    suspend fun register(
        name: String,
        email: String,
        username: String,
        password: String,
        phone: String
    ): ResponseRegister {
        val requestBody = HashMap<String, String>()
        requestBody["name"] = name
        requestBody["email"] = email
        requestBody["username"] = username
        requestBody["password"] = password
        requestBody["phone"] = phone
        return apiService.register(requestBody)
    }

    suspend fun login(username: String, password: String): ResponseLogin {
        val requestBody = HashMap<String, String>()
        requestBody["username"] = username
        requestBody["password"] = password
        return apiService.login(requestBody)
    }

    suspend fun getDetail(idUser: Int): ResponseDetailuser {
        return apiService.getUserDetail(idUser)
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun updateProfile(
        idUser: Int,
        name: String,
        address: String,
        phone: String
    ): ResponseEditProfile {
        val requestBody = HashMap<String, String>()
        requestBody["name"] = name
        requestBody["address"] = address
        requestBody["phone"] = phone
        return apiService.updateUserProfile(idUser, requestBody)
    }
//    suspend fun updateProfile(idUser: Int, currentImageUri: Uri, name:String, address: String, phone: String): ResponseEditProfile{
//
//        val requestBody = HashMap<String, String>()
//        requestBody["name"] = name
//        requestBody["address"] = address
//        requestBody["phone"] = phone
//
//        val imageFile = File(currentImageUri.path ?: "")
//        val imageRequestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
//        val imagePart = MultipartBody.Part.createFormData("url_profile", imageFile.name, imageRequestBody)
//
////        return apiService.updateUserProfile(idUser, requestBody, imagePart)
//}


    companion object{
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService, userPreference: UserPreference): UserRepository =
            instance ?: synchronized(this){
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}