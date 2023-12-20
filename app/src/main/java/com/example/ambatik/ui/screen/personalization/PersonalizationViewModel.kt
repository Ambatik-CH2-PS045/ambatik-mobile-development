package com.example.ambatik.ui.screen.personalization

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataItemPersonalisasi
import com.example.ambatik.api.response.ResponseCheckout
import com.example.ambatik.api.response.ResponsePersonalizationBatik
import com.example.ambatik.data.repository.PersonalizationRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PersonalizationViewModel(private val repository: PersonalizationRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val rekomendasi = MutableLiveData<List<DataItemPersonalisasi?>?>()

    fun personalization(userAnswer: List<Int>){
        viewModelScope.launch {
            try {
                val responsePersonalisasi = repository.personalization(userAnswer)
                rekomendasi.postValue(responsePersonalisasi.data)
                Log.d("PERSONALISASI", "$responsePersonalisasi")
            }catch (e: HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ResponsePersonalizationBatik::class.java)
                val errorMassage = errorBody?.message ?: "Terjadi kesalahan saat Submit Personalisasi"
                status.value = false
                error.postValue(errorMassage)
                Log.d("PERSONALISASI", "$e")
            }catch (e: HttpException){
                status.value = false
                error.postValue("Terjadi kesalahan saat memuat data")
                Log.d("PERSONALISASI", "$e")
            }
        }
    }
}