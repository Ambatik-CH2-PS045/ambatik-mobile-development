package com.example.ambatik.ui.components.batik

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun BatikItem(
    imgUrl: String,
    nameBatik: String,
    origin: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(colorScheme.onPrimary),
        modifier = modifier
            .size(150.dp, 255.dp)
    ) {
        Column {
            AsyncImage(
                model = imgUrl,
                contentDescription = "Batik Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(150.dp, 150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
            )
            Box(
                modifier = modifier
                    .padding(12.dp)
            ) {
                Column {
                    Text(
                        text = nameBatik,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = origin,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        modifier = modifier
                            .padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBatikItem(){
    AmbatikTheme {
        BatikItem(
            imgUrl = "",
            nameBatik = "BATIK JAWA",
            origin = "Jawa tengah"
        )
    }
}