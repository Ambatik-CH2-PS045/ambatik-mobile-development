package com.example.ambatik.ui.screen.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class QuizViewModel() {
    val error = MutableLiveData<String?>()
    val status: MutableLiveData<Boolean> = MutableLiveData()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
}