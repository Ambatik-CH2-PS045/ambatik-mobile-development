package com.example.ambatik.ui.screen.editprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.ResponseEditPhotoProfile
import com.example.ambatik.api.response.ResponseEditProfile
import com.example.ambatik.data.repository.UserRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File

class EditProfileViewModel(private val repository: UserRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()

    private val _loadingEditProfile = MutableLiveData<Boolean>()
    val loadingEditProfile: LiveData<Boolean> = _loadingEditProfile

    private val _loadingEditPhotoProfile = MutableLiveData<Boolean>()
    val loadingEditPhotoProfile: LiveData<Boolean> = _loadingEditPhotoProfile

    fun editProfile(id: Int, name: String, address: String, phone: String){
        _loadingEditProfile.postValue(true)
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
            }finally {
                _loadingEditProfile.value = false
            }
        }
    }

    fun editPhotoProfile(file: File, userid: Int){
        _loadingEditPhotoProfile.postValue(true)
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
            }finally {
                _loadingEditPhotoProfile.value = false
            }
        }
    }
}