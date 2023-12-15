package com.example.ambatik.ui.components.order

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.ui.theme.Shapes

@Composable
fun OrderItem(
    image: String,
    totalPrice: String,
    totalItem: String,
    otherItem: String,
    productName: String,
    modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row {
            AsyncImage(
                model = image,
                contentDescription = "Order Image",
                modifier = modifier
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .size(95.dp)
                    .clip(Shapes.medium)
            )
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = modifier
                    .padding(0.dp, 15.dp, 15.dp, 0.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = productName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Text(
                        text = "Rp. $totalPrice",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = modifier
                            .padding(top = 4.dp)
                    )
                    Box(
                        contentAlignment = Alignment.BottomEnd,
                        modifier = modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = otherItem,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = modifier
                                .padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewOrderItem(){
    AmbatikTheme {
        OrderItem(
            image = "",
            totalPrice = "100000",
            totalItem = "2",
            otherItem = "1",
            productName = "Batik terbaik tahun iki"
        )
    }
}