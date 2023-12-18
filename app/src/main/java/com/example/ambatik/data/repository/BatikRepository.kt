package com.example.ambatik.data.repository

import com.example.ambatik.api.response.ResponseBatik
import com.example.ambatik.api.response.ResponseDetailBatik
import com.example.ambatik.api.response.ResponsePredictBatik
import com.example.ambatik.api.retrofit.ApiService
import com.example.ambatik.data.pref.UserPreference
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class BatikRepository(private val apiService: ApiService, private val userPreference: UserPreference) {
    suspend fun getBatik(): ResponseBatik{
        return apiService.getBatik()
    }

    suspend fun getDetailBatik(idBatik: Int): ResponseDetailBatik {
        return apiService.getDetailBatik(idBatik)
    }

    suspend fun predictBatik(file: File): ResponsePredictBatik{
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
        val multipart = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestImageFile
        )
        return apiService.predictBatik(multipart)
    }

    companion object{
        @Volatile
        private var instance: BatikRepository? = null
        fun getInstance(apiService: ApiService, userPreference: UserPreference): BatikRepository =
            instance ?: synchronized(this){
                instance ?: BatikRepository(apiService, userPreference)
            }.also { instance = it }
    }
}