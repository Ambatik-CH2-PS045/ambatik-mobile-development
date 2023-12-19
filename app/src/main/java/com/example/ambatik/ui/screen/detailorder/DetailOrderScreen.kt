package com.example.ambatik.ui.screen.detailorder

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ambatik.data.factory.OrderModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.utlis.formatCurrency

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailOrderScreen(
    idOrder: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailOrderViewModel = viewModel(
        factory = OrderModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore)
) {
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))
    val detailOrder = viewModel.detailOrder.observeAsState()
    val listDetailOrder = viewModel.listOrder.observeAsState()

    LaunchedEffect(userModel.id){
        viewModel.getDetailOrder(idOrder, userModel.id)
    }

    Scaffold(
        bottomBar = {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(colorScheme.onPrimary)
            )
            LazyColumn(
                contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp),
            ){
                items(detailOrder.value ?: emptyList()){dataBottom ->
                    BottomBarContent(
                        totalItem = dataBottom?.totalItem.toString() ?: "",
                        totalPrice = dataBottom?.totalPrice.toString() ?: "",
                    )
                }
            }
        }
    ) {
        Surface(
            color = colorScheme.surface,
            modifier = modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 90.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ){
                items(listDetailOrder.value ?: emptyList()){data ->
                    DetailOrderContent(
                        name = data?.name ?: "",
                        image = data?.urlProduct ?: "",
                        price = data?.price.toString() ?: "",
                        storeName = data?.storeName ?: "",
                        quantity = data?.detailOrder?.eachQty.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBarContent(
    totalItem: String,
    totalPrice: String,
    modifier: Modifier = Modifier
){
    Column {
        Text(
            text = "Total Item: $totalItem"
        )
        Text(
            text = "Total Price: Rp. ${formatCurrency(totalPrice.toDouble())}"
        )
    }
}

@Composable
fun DetailOrderContent(
    name: String,
    image: String,
    price: String,
    storeName: String,
    quantity: String,
    modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(colorScheme.onPrimary),
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Row(
            modifier = modifier
                .padding(end = 12.dp)
        ) {
            Box(
                modifier = modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .height(150.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "DETIAL ORDER IMAGE",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .padding(top = 12.dp)
                        .size(100.dp)
                )
            }
            Box(
                modifier = modifier
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = storeName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .padding(top = 4.dp)
                    )
                    Text(
                        text = formatCurrency(price.toDouble()) ,
                        modifier = modifier
                            .padding(top = 8.dp, bottom = 4.dp)
                    )
                    Text(
                        text = "Total Item: $quantity"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailOrder(){
    AmbatikTheme {
        DetailOrderContent(
            name = "LALALA",
            image = "",
            price = "10000",
            storeName = "TOKOPEDIA",
            quantity = "5"
        )
    }
}