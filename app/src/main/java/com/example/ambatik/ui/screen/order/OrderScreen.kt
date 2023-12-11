package com.example.ambatik.ui.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.data.factory.OrderModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.order.OrderItem
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun OrderScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: GetOrderViewModel = viewModel(
        factory = OrderModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore),
    modifier: Modifier = Modifier
) {
    val orderListState = viewModel.orderList.observeAsState()
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))

    LaunchedEffect(userModel.id){
        viewModel.getOrder(userModel.id)
    }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            items(orderListState.value ?: emptyList()){ data ->
                OrderItem(
                    image = data.productUrlProduct,
                    totalPrice = data.totalPrice.toString(),
                    totalItem = data.totalItem.toString(),
                    otherItem = data.otherItem.toString(),
                    productName = data.productName
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewOrderScreen(){
    AmbatikTheme {
        OrderScreen()
    }
}