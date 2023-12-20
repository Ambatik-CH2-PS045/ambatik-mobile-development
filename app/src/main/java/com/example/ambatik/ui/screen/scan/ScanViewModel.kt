package com.example.ambatik.ui.screen.scan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambatik.api.response.DataPredictBatik
import com.example.ambatik.api.response.ProductsItemBatikScan
import com.example.ambatik.api.response.ResponsePredictBatik
import com.example.ambatik.data.repository.BatikRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File

class ScanViewModel(private val repository: BatikRepository): ViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val detailScanBatik = MutableLiveData<DataPredictBatik?>()
    val akurasiBatik = MutableLiveData<ResponsePredictBatik?>()
    val rekomendasiProduk = MutableLiveData<List<ProductsItemBatikScan?>?>()

    fun scanBatik(file: File){
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val responseScanBatik = repository.predictBatik(file)
                status.postValue(true)
                detailScanBatik.postValue(responseScanBatik.data)
                akurasiBatik.postValue(responseScanBatik)
                rekomendasiProduk.postValue(responseScanBatik.products)
                Log.d("SCAN BATIK", "$responseScanBatik")
            }catch (e: HttpException){
                _loading.value = false
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ResponsePredictBatik::class.java)
                val errorMessage = errorBody?.message ?: "Terjadi kesalahan saat SCAN BATIK"
                error.postValue(errorMessage)
                status.postValue(false)
                Log.d("SCAN BATIK", "$e")
            }catch (e: Exception) {
                _loading.value = false
                error.postValue("Terjadi kesalahan saat edit profile")
                status.postValue(false)
                Log.d("SCAN BATIK", "$e")
            }finally {
                _loading.value = false
            }
        }
    }
}