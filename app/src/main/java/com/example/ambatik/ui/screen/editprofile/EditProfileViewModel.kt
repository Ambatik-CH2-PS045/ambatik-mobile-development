package com.example.ambatik.ui.screen.editprofile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.ResponseEditPhotoProfile
import com.example.ambatik.api.response.ResponseEditProfile
import com.example.ambatik.data.repository.UserRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.HttpException
import java.io.File

class EditProfileViewModel(private val repository: UserRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()

    fun editProfile(id: Int, name: String, address: String, phone: String){
        viewModelScope.launch {
            try {
                val editProfileResponse = repository.updateProfile(id, name, address, phone)
                status.postValue(true)
                Log.d("EDIT PROFILE", "$editProfileResponse")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ResponseEditProfile::class.java)
                val errorMessage = errorBody?.message ?: "Terjadi kesalahan saat edit profile"
                error.postValue(errorMessage)
                status.postValue(false)
                Log.d("EDIT PROFILE", "$e")
            }catch (e: Exception) {
                error.postValue("Terjadi kesalahan saat edit profile")
                status.postValue(false)
                Log.d("LOGIN", "$e")
            }
        }
    }

    fun editPhotoProfile(file: File, userid: Int){
        viewModelScope.launch {
            try {
                val responseEditPhotoProfile = repository.editPhotoProfile(file, userid)
                status.postValue(true)
                Log.d("EDIT PHOTO PROFILE", "$responseEditPhotoProfile")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ResponseEditPhotoProfile::class.java)
                val errorMessage = errorBody?.message ?: "Terjadi kesalahan saat edit profile"
                error.postValue(errorMessage)
                status.postValue(false)
                Log.d("EDIT PHOTO PROFILE", "$e")
            }catch (e: Exception) {
                error.postValue("Terjadi kesalahan saat edit profile")
                status.postValue(false)
                Log.d("EDIT PHOTO PROFILE", "$e")
            }
        }
    }
}