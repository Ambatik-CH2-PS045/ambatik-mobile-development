package com.example.ambatik.ui.components.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    modifier : Modifier = Modifier,
    navigateToStartQuiz: () -> Unit,
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    if(openAlertDialog.value){
        AlertDialog(
            onDismissRequest = { openAlertDialog.value = false },
            title = {
                Text(
                    text = "Kuis $name"
                )
            },
            text = {
                Text(
                    text = "Apakah kamu sudah siap untuk melakukan kuis?"
                )
            },
            confirmButton = {
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        openAlertDialog.value = false
                        navigateToStartQuiz()
                    }
                ) {
                    Text(
                        text = "Mulai"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { openAlertDialog.value = false }
                ) {
                    Text(
                        text = "Kembali"
                    )
                }
            }
        )
    }

    Column(
        modifier = modifier
    ) {
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
        ){
            Row(
                modifier = modifier
            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(16.dp, 4.dp)
                ) {
                    Box(
                        modifier = modifier
                            .height(100.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Button(
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                openAlertDialog.value = true
                            },
                            modifier = modifier
                                .padding(16.dp, 16.dp, 0.dp, 16.dp)
                                .size(125.dp, 50.dp)
                        ) {
                            Text(
                                text = "Mulai",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = modifier
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = name,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 24.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Box(
                            modifier = modifier
                                .padding(top = 4.dp)
                        ) {
                            Text(
                                text = "5 Soal"
                            )
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
            navigateToStartQuiz = {}
        )
    }
}