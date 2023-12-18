package com.example.ambatik.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ambatik.data.repository.CartRepository
import com.example.ambatik.di.Injection
import com.example.ambatik.ui.screen.cart.CartViewModel
import com.example.ambatik.ui.screen.detailshopping.AddToCartViewModel

class CartModelFactory(private val repository: CartRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(AddToCartViewModel::class.java) -> {
                AddToCartViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unkown ViewModel class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: CartModelFactory? = null

        fun getInstance(context: Context): CartModelFactory =
            instance ?: synchronized(this){
                instance ?: CartModelFactory(Injection.provideCartRepository(context))
            }.also { instance = it }
    }
}