package com.example.ambatik.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ambatik.data.repository.PersonalizationRepository
import com.example.ambatik.di.Injection
import com.example.ambatik.ui.screen.personalization.PersonalizationViewModel

class PersonalizationFactory(private val repository: PersonalizationRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(PersonalizationViewModel::class.java) -> {
                PersonalizationViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unkown ViewModel class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: PersonalizationFactory? = null

        fun getInstance(context: Context): PersonalizationFactory =
            instance ?: synchronized(this){
                instance ?: PersonalizationFactory(Injection.providePersonalizationRepository(context))
            }.also { instance = it }
    }
}