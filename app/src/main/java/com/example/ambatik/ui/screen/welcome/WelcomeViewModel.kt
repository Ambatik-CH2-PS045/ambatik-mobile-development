package com.example.ambatik.ui.screen.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.repository.UserRepository

class WelcomeViewModel(private val repository: UserRepository): ViewModel() {
    fun getSession(): LiveData<UserModel>{
        return repository.getSession().asLiveData()
    }
}