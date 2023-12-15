package com.example.ambatik.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ambatik.data.repository.OrderRepository
import com.example.ambatik.di.Injection
import com.example.ambatik.ui.screen.cart.AddOrderViewModel
import com.example.ambatik.ui.screen.detailorder.DetailOrderViewModel
import com.example.ambatik.ui.screen.order.OrderViewModel

class OrderModelFactory(private val repository: OrderRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(AddOrderViewModel::class.java) -> {
                AddOrderViewModel(repository) as T
            }
            modelClass.isAssignableFrom(OrderViewModel::class.java) -> {
                OrderViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailOrderViewModel::class.java) -> {
                DetailOrderViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unkown ViewModel class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: OrderModelFactory? = null

        fun getInstance(context: Context): OrderModelFactory =
            instance ?: synchronized(this){
                instance ?: OrderModelFactory(Injection.provideOrderRepository(context))
            }.also { instance = it }
    }
}