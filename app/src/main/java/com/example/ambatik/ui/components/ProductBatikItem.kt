package com.example.ambatik.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun ProductBatikItem(
    image: String,
    nameProduct: String,
    price: Int,
    store: String,
    rating: Double,
    productSold: Int,
    modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(Color.Black),
        modifier = modifier
            .width(185.dp)
            .height(280.dp)
    ) {
        Column {
            AsyncImage(
                model = image,
                contentDescription = "Image Product",
                modifier = modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Text(
                text = nameProduct,
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .padding(12.dp, 8.dp, 12.dp, 0.dp)
            )
            Text(
                text = "Rp. $price",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .padding(12.dp, 0.dp, 12.dp, 4.dp)
            )
            Text(
                text = store,
                color = Color.White,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .padding(12.dp, 0.dp, 12.dp, 6.dp)
            )
            Box(
                modifier = modifier
                    .padding(12.dp, 0.dp, 12.dp, 4.dp)
                    .height(15.dp)
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Icon Rating",
                        tint = Color.Yellow,
                        modifier = modifier
                            .size(15.dp)
                    )
                    Text(
                        text = rating.toString(),
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = modifier

                    )
                    Divider(
                        color = Color.White,
                        modifier = modifier
                            .padding(4.dp, 0.dp)
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    Text(
                        text = "$productSold terjual",
                        color = Color.White,
                        fontSize = 10.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProductBatikItem(){
    AmbatikTheme {
        ProductBatikItem(
            image = "",
            nameProduct = "LAPTOP GAMING TERBAIK TAHUN INI",
            price = 15000000,
            store = "NVIDIA GAMING OFFICIAL",
            rating = 5.0,
            productSold = 30000
        )
    }
}