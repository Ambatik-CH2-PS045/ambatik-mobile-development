package com.example.ambatik.ui.screen.cart

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.api.response.DataItemCart
import com.example.ambatik.data.factory.CartModelFactory
import com.example.ambatik.data.factory.OrderModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.cart.CartItem
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.utlis.formatCurrency
import kotlinx.coroutines.runBlocking
import java.util.logging.Handler

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = viewModel(
        factory = CartModelFactory.getInstance(LocalContext.current)
    ),
    viewModelOrder: AddOrderViewModel = viewModel(
        factory = OrderModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore)
){
    val dataCartListState = viewModel.dataCart.observeAsState()
    val statusState by viewModel.status.observeAsState(false)
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))
    val context = LocalContext.current

    LaunchedEffect(userModel.id){
        viewModel.getCart(userModel.id)
    }

    Scaffold(
        bottomBar = {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.Transparent)
            )
            dataCartListState.value.let { data ->
                val totalQty = data?.totalQty
                val grandTotalOrder = data?.grandTotal
                val dataItem: List<DataItemCart?> = data?.data ?: emptyList()
                val eachQuantity = dataItem.map { it?.totalQty?.toInt() }
                val eachPriceList = dataItem.map { it?.price }
                val eachProduct = dataItem.map { it?.id }
                Log.d("ORDER", "ORDER MASUK, idUser:$totalQty, grandTotal: $grandTotalOrder, idProd:${eachProduct.size}, priceProd:${eachPriceList.size}, qtyProd:${eachQuantity.size}")
                if (grandTotalOrder != null) {
                    BottomContent(
                        totalPrice = if (grandTotalOrder == 0) "" else formatCurrency(grandTotalOrder.toDouble()),
                        onAddToOrder = {
                            if (data.data?.isNotEmpty() == true){
                                viewModelOrder.checkout(totalQty, grandTotalOrder, userModel.id, eachQuantity, eachPriceList, eachProduct)
                                android.os.Handler().postDelayed({
                                    viewModel.getCart(userModel.id)
                                }, 100)
                            }else{
                                Toast.makeText(context, "Cart kamu masih kosong", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = modifier
                    )
                }
            }
        }
    ) {
        Surface(
            color = colorScheme.surface,
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 80.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ){
                    items(dataCartListState.value?.data ?: emptyList()){data ->
                        CartItem(
                            idProduct = data?.id ?: 0,
                            name = data?.name ?: "",
                            image = data?.urlProduct ?: "",
                            price = data?.price.toString() ?: "",
                            totalPrice = data?.totalPrice ?: "",
                            totalQuantity = data?.totalQty ?: "",
                            storeName = data?.storeName ?: "",
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun BottomContent(
    totalPrice: String,
    onAddToOrder : () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .padding(20.dp, 12.dp, 20.dp, 12.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(0.5f),
            contentAlignment = Alignment.CenterStart
        ) {
            Column{
                Text(
                    text = "Total Price",
                    color = colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = totalPrice,
                    color = colorScheme.onSurface,
                )
            }
        }
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = {
                    onAddToOrder()
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorScheme.primary),
                modifier = modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "Order Now",
                    color = colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomCart(){
    AmbatikTheme {
        BottomContent(totalPrice = "150000", onAddToOrder = {})
    }
}

@Preview
@Composable
fun PreviewCartScreen(){
    AmbatikTheme {
        CartScreen()
    }
}