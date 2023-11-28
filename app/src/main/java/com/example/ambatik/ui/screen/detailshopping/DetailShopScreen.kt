package com.example.ambatik.ui.screen.detailshopping

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ambatik.R
import com.example.ambatik.ui.components.CountItem
import com.example.ambatik.ui.components.OrderButton
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun DetailShopScreen() {


}

@Composable
fun DetailShopContent(
    image: String,
    nameProduct: String,
    price: String,
    store: String,
    rating: String,
    productSold: Int,
    description: String,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box(
            ){
                AsyncImage(
                    model = image,
                    contentDescription = "Image Detail Article",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Box(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ){
                Column {
                    Box{
                        Row {
                            Text(
                                text = price,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.ExtraBold
                                ),
                            )
                            Box (
                                modifier = modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ){
                                Icon(
                                    imageVector = Icons.Default.ThumbUp,
                                    contentDescription = stringResource(R.string.like),
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                    Box(
                        modifier = modifier
                        .fillMaxWidth()
                    ){
                        Column {
                            Text(text = nameProduct, style = MaterialTheme.typography.bodyLarge)
                            Text(
                                text = store,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Box(
                                modifier = modifier
                                    .padding(8.dp)
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        modifier = modifier
                                    )
                                    Text(
                                        text = rating,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    )
                                }
                            }
                        }
                    }
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )
                }
            }

        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.LightGray))
        Row (
            modifier = Modifier.padding(16.dp)
        ){
            CountItem(
                orderId = 1,
                orderCount = orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(bottom = 16.dp)
            )
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                OrderButton(
                    modifier = Modifier
                        .width(200.dp),
                    text = stringResource(R.string.add_to_cart),
                    enabled = orderCount > 0,
                    onClick = {
                        onAddToCart(orderCount)
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailShopContentPreview() {
    AmbatikTheme {
        DetailShopContent(
            "",
            "Batik Keris Cirebon",
            "Rp. 7500",
            "Batik Jaya",
            "4.3",
            100,
            stringResource(R.string.lorem_ipsum),
            1,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}