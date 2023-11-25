package com.example.ambatik.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.ui.theme.Shapes

@Composable
fun ArticleItem(
    image: String,
    title: String,
    createAt: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
        ) {
            AsyncImage(
                model = image,
                contentDescription = "Article Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(15.dp, 15.dp, 0.dp, 15.dp)
                    .size(115.dp, 170.dp)
                    .clip(Shapes.medium)
            )
            Column {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = modifier
                        .padding(15.dp, 15.dp, 15.dp, 0.dp)
                )
                Row (
                    verticalAlignment = Alignment.Bottom,
                    modifier = modifier
                        .fillMaxWidth()
                ){
                    Box(
                        modifier = modifier
                            .padding(15.dp, 0.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Divider(
                            modifier = modifier
                                .padding(0.dp, 15.dp, 100.dp, 15.dp),
                            color = Color.White
                        )
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Text(
                                text = createAt,
                                fontSize = 10.sp,
                                color = Color.White,
                                textAlign = TextAlign.Right,
                                modifier = modifier
                                    .padding(15.dp)
                            )
                        }

                    }
                }
                Text(
                    text = description,
                    color = Color.White,
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier
                        .padding(15.dp, 0.dp, 15.dp, 0.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewArticleItem(){
    AmbatikTheme {
        ArticleItem(
            image = "",
            title = "Yuk, Rayakan Hari Batik Nasional dalam Rangkaian Acara Keren GANTARI!",
            createAt = "2023-11-23",
            description = "Dalam penyelenggaraan kali ini, LAKON Indonesia akan mempresentasikan koleksi yang lebih matang dan lebih dalam, berupa 125 koleksi pakaian siap pakai yang akan diperagakan oleh 100 orang model.")
    }
}