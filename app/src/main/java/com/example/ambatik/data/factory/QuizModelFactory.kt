package com.example.ambatik.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ambatik.data.repository.QuizRepository
import com.example.ambatik.di.Injection
import com.example.ambatik.ui.screen.detailshopping.DetailShopViewModel
import com.example.ambatik.ui.screen.quiz.QuizViewModel
import com.example.ambatik.ui.screen.shopping.ShoppingViewModel

class QuizModelFactory(private val repository: QuizRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(QuizViewModel::class.java) -> {
                QuizViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unkown ViewModel class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: QuizModelFactory? = null

        fun getInstance(context: Context): QuizModelFactory =
            instance ?: synchronized(this){
                instance ?: QuizModelFactory(Injection.provideQuizRepository(context))
            }.also { instance = it }
    }
}