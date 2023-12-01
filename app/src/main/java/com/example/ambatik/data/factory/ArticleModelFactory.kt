package com.example.ambatik.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ambatik.data.repository.ArticleRepository
import com.example.ambatik.data.repository.UserRepository
import com.example.ambatik.di.Injection
import com.example.ambatik.ui.screen.articel.ArticleViewModel
import com.example.ambatik.ui.screen.articlelike.ArticleLikeViewModel
import com.example.ambatik.ui.screen.detailarticle.DetailArticleViewModel
import com.example.ambatik.ui.screen.login.LoginViewModel
import com.example.ambatik.ui.screen.profile.ProfileViewModel
import com.example.ambatik.ui.screen.register.RegisterViewModel
import com.example.ambatik.ui.screen.welcome.WelcomeViewModel


class ArticleModelFactory(private val repository: ArticleRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailArticleViewModel::class.java) -> {
                DetailArticleViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ArticleLikeViewModel::class.java) -> {
                ArticleLikeViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unkown ViewModel class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: ArticleModelFactory? = null

        fun getInstance(context: Context): ArticleModelFactory =
            instance ?: synchronized(this){
                instance ?: ArticleModelFactory(Injection.provideArticleRepository(context))
            }.also { instance = it }
    }
}