package com.example.ambatik.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun CartItem(
    image: String,
    nameProduct: String,
    count: String,
    countPrice: String,
    modifier: Modifier = Modifier
) {

    var orderCount by rememberSaveable { mutableStateOf(count.toInt()) }

    Card(
        colors = CardDefaults.cardColors(Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .height(115.dp)
    ) {
        Box(modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
        )
        {
            Row {
                Box(modifier = modifier
                    .width(112.dp)
                ) {
                    AsyncImage(
                        model = image,
                        contentDescription = "Image Product"
                    )
                }
                Box (modifier = modifier
                    .padding(16.dp, 0.dp, 12.dp, 0.dp)
                    .fillMaxHeight()
                ){
                    Column{
                        Text(
                            text = nameProduct,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = modifier
                                .padding(5.dp, 0.dp, 0.dp, 8.dp)
                        )
                        Text(
                            text = "Rp. $countPrice",
                            fontSize = 14.sp,
                            color = Color.White,
                            modifier = modifier
                                .padding(start = 5.dp)
                        )
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier
                                .fillMaxHeight()
                        ){
                            Row {
                                Text(
                                    text = ""
                                )
                                CountItem(
                                    orderId = 1,
                                    orderCount = orderCount,
                                    onProductIncreased = { orderCount++ },
                                    onProductDecreased = { if (orderCount > 0) orderCount-- }
                                )
                            }
                        }
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
            "",
            "Batik Baru Motif Megamendung",
            "1",
            "100000",
        )
    }
}