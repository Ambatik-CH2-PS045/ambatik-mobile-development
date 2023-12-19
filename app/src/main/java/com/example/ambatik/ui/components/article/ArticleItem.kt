package com.example.ambatik.ui.components.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
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
import com.example.ambatik.utlis.convertDateFormat

@Composable
fun ArticleItem(
    image: String,
    title: String,
    createAt: String,
    totalLike: String,
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
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .size(95.dp)
                    .clip(Shapes.medium)
            )
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = modifier
                    .padding(0.dp, 15.dp, 15.dp, 0.dp)
            ) {
                Column {
                    Text(
                        text = title,
                        color = colorScheme.onSurface,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Box(
                        modifier = modifier
                            .padding(0.dp, 30.dp, 0.dp, 0.dp)
                    ) {
                        Row{
                            Icon(
                                imageVector = Icons.Default.Favorite ,
                                tint = Color.Red,
                                contentDescription = "Icon Like Article",
                                modifier = modifier
                                    .size(17.dp)
                            )
                            Text(
                                text = totalLike.toString(),
                                fontSize = 12.sp,
                                color = colorScheme.onSurface,
                                modifier = modifier
                                    .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            )
                            Box(
                                modifier = modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Text(
                                    text = convertDateFormat(createAt),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = colorScheme.onSurface,
                                    textAlign = TextAlign.Right,
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
fun PreviewArticleItem(){
    AmbatikTheme {
        ArticleItem(
            image = "",
            title = "Yuk, Rayakan Hari Batik Nasional dalam Rangkaian Acara Keren GANTARI!",
            createAt = "2023-11-23",
            totalLike = "1"
        )
    }
}