package com.example.ambatik.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ambatik.data.repository.UserRepository
import com.example.ambatik.di.Injection
import com.example.ambatik.ui.screen.login.LoginViewModel
import com.example.ambatik.ui.screen.register.RegisterViewModel

class UserModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->{
                LoginViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unkown ViewModel class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: UserModelFactory? = null

        fun getInstance(context: Context): UserModelFactory =
            instance ?: synchronized(this){
                instance ?: UserModelFactory(Injection.provideUserRepository(context))
            }.also { instance = it }
    }
}