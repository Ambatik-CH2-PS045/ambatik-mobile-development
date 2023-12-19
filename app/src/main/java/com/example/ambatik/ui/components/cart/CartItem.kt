package com.example.ambatik.ui.components.cart

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ambatik.data.factory.CartModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.screen.cart.CartViewModel
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.utlis.formatCurrency

@Composable
fun CartItem(
    idProduct: Int,
    name: String,
    image: String,
    price: String,
    totalPrice: String,
    totalQuantity: String,
    storeName: String,
    viewModel: CartViewModel = viewModel(
        factory = CartModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore),
    modifier: Modifier = Modifier
){
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))
    val commandAdd = "add"
    val commadDecrease = "reduce"

    Surface(
        color = Color.White,
        modifier = modifier
            .wrapContentSize()
    ) {
        Column(
            modifier = modifier
                .padding(20.dp, 12.dp, 20.dp, 16.dp)
        ) {
            Text(
                text = storeName,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onSurface,
            )
            Box(
                modifier = modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    AsyncImage(
                        model = image,
                        contentDescription = "Image Cart",
                        modifier = modifier
                            .size(100.dp)
                    )
                    Column(
                        modifier = modifier
                            .padding(start = 12.dp)
                    ) {
                        Text(
                            text = name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = colorScheme.onSurface,
                        )
                        Text(
                            text = formatCurrency(totalPrice.toDouble()) ,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = colorScheme.onSurface,
                            modifier = modifier
                                .padding(top = 4.dp)
                        )
                        CountItem(
                            orderId = idProduct,
                            orderCount = totalQuantity.toInt(),
                            onProductIncreased = {
                                viewModel.getCart(userModel.id)
                                viewModel.changeQtyCart(userModel.id, idProduct, commandAdd)
                                Log.d("CartItem", "Increased: User ID - ${userModel.id}, Product ID - $idProduct")
                            },
                            onProductDecreased = {
                                viewModel.getCart(userModel.id)
                                viewModel.changeQtyCart(userModel.id, idProduct, commadDecrease)
                                Log.d("CartItem", "Decreased: User ID - ${userModel.id}, Product ID - $idProduct")
                            },
                            modifier = modifier
                                .padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCartItem(){
    AmbatikTheme {
        CartItem(
            1,
            "Batik Baru Motif Megamendung",
            "",
            "5",
            "500000",
            "10",
            "BATIK PEDIA",
        )
    }
}