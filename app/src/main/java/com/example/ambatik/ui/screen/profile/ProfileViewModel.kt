package com.example.ambatik.ui.screen.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.data.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel(private val repository: UserRepository): ViewModel() {
    val status: MutableLiveData<Boolean> = MutableLiveData()
    fun logout(){
        viewModelScope.launch {
            try {
                repository.logout()
                status.postValue(true)
                Log.d("LOGOUT", "LOGOUT BERHASIL")
            }catch (e: HttpException){
                status.postValue(false)
                Log.d("LOGOUT", "LOGOUT GAGAL")
            }
        }
    }
}