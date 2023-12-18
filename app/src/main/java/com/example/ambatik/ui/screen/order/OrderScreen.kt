package com.example.ambatik.ui.screen.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    viewModel: OrderViewModel = viewModel(
        factory = OrderModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore),
    modifier: Modifier = Modifier,
    navigateToDetailOrder: (Int) -> Unit
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
        Column {
            Text(
                text = "History Belanja",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            )
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ){
                items(orderListState.value ?: emptyList()){ data ->
                    var otherItem by remember { mutableStateOf("") }
                    if(data.otherItem == 0){
                        otherItem = ""
                    }else{
                        otherItem = "+${data.otherItem} product lainnya"
                    }
                    OrderItem(
                        image = data.productUrlProduct,
                        totalPrice = data.totalPrice.toString(),
                        totalItem = data.totalItem.toString(),
                        otherItem = otherItem,
                        productName = data.productName,
                        modifier = modifier
                            .clickable {
                                navigateToDetailOrder(data.id)
                            }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewOrderScreen(){
    AmbatikTheme {
        OrderScreen(navigateToDetailOrder = {})
    }
}