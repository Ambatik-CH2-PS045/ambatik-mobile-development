package com.example.ambatik.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
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
    price: Int,
    count: Int,
    rating: Double,
    countPrice: Int,
    modifier: Modifier = Modifier
) {

//    var orderCount by rememberSaveable { mutableStateOf(count) }

    Card(
        colors = CardDefaults.cardColors(Color.LightGray),
        modifier = modifier
            .width(336.dp)
            .height(109.dp)
    ) {
        Box(modifier = modifier
            .padding(8.dp)
            .width(320.dp))
        {
            Row {
                Box(modifier = modifier
                    .width(112.dp)) {
                    AsyncImage(
                        model = image,
                        contentDescription = "Image Product"
                    )
                }
                Box (modifier = modifier
                    .padding(16.dp, 0.dp, 16.dp, 0.dp)
                    .fillMaxHeight()
                ){
                    Column{
                        Text(
                            text = nameProduct,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            modifier = modifier
                                .padding(0.dp, 0.dp, 0.dp, 8.dp)
                        )
                        Box (
                            modifier = modifier
                                .padding(0.dp, 0.dp, 12.dp, 4.dp)
                                .height(17.dp)
                        ) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Icon Rating",
                                    tint = Color.Yellow,
                                    modifier = modifier
                                        .size(16.dp)
                                )
                                Text(
                                    text = rating.toString(),
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Text(
                            text = "Rp. $countPrice",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
//                Box(
//                    modifier = modifier
//                        .fillMaxHeight()
//                ){
//                    Column {
//                        Icon(
//                            imageVector = Icons.Default.Delete,
//                            contentDescription = "Delete Cart",
//                            tint = Color.White,
//                            modifier = modifier
//                                .size(16.dp)
//                        )
//                        CountItem(
//                            orderId = 1,
//                            orderCount = orderCount,
//                            onProductIncreased = { orderCount++ },
//                            onProductDecreased = { if (orderCount > 0) orderCount-- }
//                        )
//                    }
//                }
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
            1,
            1,
            1.0,
            10000,
        )
    }
}