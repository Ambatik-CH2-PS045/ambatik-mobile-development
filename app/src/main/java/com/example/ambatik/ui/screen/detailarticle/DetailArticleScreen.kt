package com.example.ambatik.ui.screen.detailarticle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun DetailArticleScreen(
    articleId: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
//        DetailArticleContent(
//            image = ,
//            title = ,
//            author = ,
//            createAt = ,
//            totalLike = ,
//            description = ,
//            modifier =
//        )
    }

}

@Composable
fun DetailArticleContent(
    image: String,
    title: String,
    author: String,
    createAt: String,
    totalLike: String,
    description: String,
    modifier: Modifier = Modifier
){
    Column {
        Box {
            AsyncImage(
                model = image,
                contentDescription = "Image Detail Article",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            )
            Box(
                modifier = modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ){
                Box(
                    modifier = modifier
                        .padding(15.dp, 15.dp)
                        .size(75.dp, 50.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .alpha(0.5f)
                        .background(Color.Black)
                )
                Box(
                    modifier = modifier
                        .padding(25.dp, 15.dp)
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ){
                    Row{
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            tint = Color.White,
                            contentDescription = "Icon Like Article",
                        )
                        Text(
                            text = totalLike.toString(),
                            color = Color.White,
                            modifier = modifier
                                .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        )

                    }
                }

            }
        }
        Box(
            modifier = modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Column {
                Box {
                    Row {
                        Text(
                            text = author,
                            color = Color.Gray
                        )
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                text = createAt,
                                color = Color.Gray,
                                textAlign = TextAlign.Right,
                            )
                        }
                    }
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    ),
                    color = Color.White,
                    modifier = modifier
                        .padding(0.dp, 12.dp, 0.dp, 20.dp)
                )
                Text(
                    text = description,
                    color = Color.White,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailArticle(){
    AmbatikTheme {
        DetailArticleContent(
            image = "",
            title = "Yuk, Rayakan Hari Batik Nasional dalam Rangkaian Acara Keren GANTARI!",
            createAt = "2023-11-23",
            totalLike = "1",
            author = "Wiguna Wijaya",
            description = "Dalam penyelenggaraan kali ini, LAKON Indonesia akan mempresentasikan koleksi yang lebih matang dan lebih dalam, berupa 125 koleksi pakaian siap pakai yang akan diperagakan oleh 100 orang model.",
        )
    }
}