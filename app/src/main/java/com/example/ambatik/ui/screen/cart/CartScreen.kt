package com.example.ambatik.ui.screen.cart

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))
    val context = LocalContext.current

    LaunchedEffect(userModel.id){
        viewModel.getCart(userModel.id)

    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
//            contentPadding = PaddingValues(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ){
            items(dataCartListState.value?.data ?: emptyList()){data ->
                CartItem(
                    name = data?.name ?: "",
                    image = data?.urlProduct ?: "",
                    totalPrice = data?.totalPrice ?: "",
                    totalQuantity = data?.totalQty ?: "",
                    storeName = data?.storeName ?: ""
                )

//                if (dataCartListState.value?.data?.size != null){
//                    cartState.value?.forEach { data ->
//                        if (data != null) {
//                            CartItem(
//                                name = data.name ?: "",
//                                image = data.urlProduct ?: "",
//                                totalPrice = data.totalPrice ?: "",
//                                totalQuantity = data.totalQty ?: "",
//                                storeName = data.storeName ?: ""
//                            )
//                        }
//                    }
//                }else{
//
//                }
            }
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