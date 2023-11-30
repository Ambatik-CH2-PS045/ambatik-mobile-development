package com.example.ambatik.ui.screen.cart

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.data.factory.CartModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.CartItem
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun CartScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = viewModel(
        factory = CartModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore)
){
    val dataCartListState = viewModel.dataCart.observeAsState()
    val cartState = viewModel.listCart.observeAsState()
    val statusState by viewModel.status.observeAsState(false)
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("Samting", "Samting", false))

    val context = LocalContext.current

    LaunchedEffect(Unit){
        viewModel.getCart(userModel.id)
    }

    if (dataCartListState.value?.data?.size != null){
        cartState.value?.forEach { data ->
            if (data != null) {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = modifier
                        .fillMaxSize()
                ){
                    CartItem(
                        image = data.urlProduct ?: "",
                        nameProduct = data.name ?: "",
                        count = data.totalQty ?: "",
                        countPrice = data.totalPrice ?: ""
                    )
                }

            }
        }
    }else{
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = modifier
                .fillMaxSize()
        ) {

        }
    }
}

@Preview
@Composable
fun PreviewCartScreen(){
    AmbatikTheme {
        CartScreen()
    }
}