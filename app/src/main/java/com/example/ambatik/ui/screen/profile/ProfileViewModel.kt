package com.example.ambatik.ui.screen.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataDetailUser
import com.example.ambatik.api.response.ResponseDetailuser
import com.example.ambatik.data.repository.UserRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel(private val repository: UserRepository): ViewModel() {
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val detailUser = MutableLiveData<DataDetailUser?>()
    val statusDetailUser: MutableLiveData<Boolean> = MutableLiveData()
    val error = MutableLiveData<String?>()

    fun getDetailUser(id: Int){
        viewModelScope.launch {
            try{
                val detailUserResponse = repository.getDetail(id)
                detailUser.postValue(detailUserResponse.data)
                status.postValue(true)
                Log.d("DETAIL USER", "$detailUser")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                Log.e("DETAIL USER", "Error response: $jsonInString")
                val errorBody = Gson().fromJson(jsonInString, ResponseDetailuser::class.java)
                error.postValue(errorBody.message)
                statusDetailUser.postValue(false)
                Log.d("DETAIL USER", "$e")
            }catch (e: HttpException){
                error.postValue("Terjadi kesalahan saat memuat data")
                statusDetailUser.postValue(false)
                Log.d("DETAIL USER", "$e")
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            try {
                repository.logout()
                val userModelAfterLogout = repository.getSession()
                status.postValue(true)
                Log.d("LOGOUT", "LOGOUT BERHASIL")
            }catch (e: HttpException){
                status.postValue(false)
                Log.d("LOGOUT", "LOGOUT GAGAL")
            }
        }
    }
}