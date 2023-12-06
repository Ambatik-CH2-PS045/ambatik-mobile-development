package com.example.ambatik.ui.components.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ambatik.ui.components.article.ArticleItem
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.ui.theme.Shapes

@Composable
fun QuizItem(
    name: String,
    level : String,
    modifier : Modifier = Modifier,
    ) {
    Surface (
        color = Color.White,
        modifier = modifier
            .wrapContentSize()
    ) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = modifier
            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = modifier
                            .height(100.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Button(
                            onClick = {},
                            modifier = modifier
                                .padding(16.dp, 16.dp, 16.dp, 16.dp)
                        ) {
                            Text(
                                text = "Mulai",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }

                    }
                    Column(
                        modifier = modifier
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = name,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 24.sp,
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
                            Row {
                                Text(
                                    text = level,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 12.sp,
                                    fontStyle = FontStyle.Italic
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
        QuizItem(
            name = "Batik Nusantara",
            level = "Level Easy"
        )
    }
}