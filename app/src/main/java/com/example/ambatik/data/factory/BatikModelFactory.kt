package com.example.ambatik.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ambatik.data.repository.BatikRepository
import com.example.ambatik.di.Injection
import com.example.ambatik.ui.screen.articel.BatikViewModel
import com.example.ambatik.ui.screen.detailbatik.DetailBatikViewModel
import com.example.ambatik.ui.screen.scan.ScanViewModel

class BatikModelFactory(private val repository: BatikRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(BatikViewModel::class.java) -> {
                BatikViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailBatikViewModel::class.java) -> {
                DetailBatikViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ScanViewModel::class.java) -> {
                ScanViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unkown ViewModel class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: BatikModelFactory? = null

        fun getInstance(context: Context): BatikModelFactory =
            instance ?: synchronized(this){
                instance ?: BatikModelFactory(Injection.provideBatikRepository(context))
            }.also { instance = it }
    }
}