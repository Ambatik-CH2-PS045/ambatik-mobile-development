package com.example.ambatik.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ambatik.data.repository.ShopRepository
import com.example.ambatik.di.Injection
import com.example.ambatik.ui.screen.articel.ArticleViewModel
import com.example.ambatik.ui.screen.detailarticle.DetailArticleViewModel
import com.example.ambatik.ui.screen.detailshopping.DetailShopViewModel
import com.example.ambatik.ui.screen.shopping.ShoppingViewModel

class ShopModelFactory(private val repository: ShopRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(ShoppingViewModel::class.java) -> {
                ShoppingViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailShopViewModel::class.java) -> {
                DetailShopViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unkown ViewModel class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: ShopModelFactory? = null

        fun getInstance(context: Context): ShopModelFactory =
            instance ?: synchronized(this){
                instance ?: ShopModelFactory(Injection.provideShopRepository(context))
            }.also { instance = it }
    }
}