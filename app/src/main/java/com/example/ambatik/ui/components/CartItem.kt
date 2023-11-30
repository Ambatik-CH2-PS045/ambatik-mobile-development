package com.example.ambatik.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ambatik.ui.theme.AmbatikTheme
@Composable
fun CartItem(
    name: String,
    image: String,
    totalPrice: String,
    totalQuantity: String,
    storeName: String,
    modifier: Modifier = Modifier
){
    var orderCount by rememberSaveable { mutableStateOf(totalQuantity.toInt()) }
    Surface(
        color = Color.Black,
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
                color = Color.White,
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
                            color = Color.White,
                        )
                        Text(
                            text = "Rp. $totalPrice",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White,
                            modifier = modifier
                                .padding(top = 4.dp)
                        )
                        CountItem(
                            orderId = 1,
                            orderCount = totalQuantity.toInt(),
                            onProductIncreased = { orderCount++ },
                            onProductDecreased = { if (orderCount > 0) orderCount-- },
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
            "Batik Baru Motif Megamendung",
            "",
            "500000",
            "10",
            "BATIK PEDIA"
        )
    }
}