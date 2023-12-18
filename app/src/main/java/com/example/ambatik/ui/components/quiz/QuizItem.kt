package com.example.ambatik.ui.components.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambatik.api.response.QuizHistoriesItem

@Composable
fun QuizItem(
    name: String,
    quizHistories: List<QuizHistoriesItem?>?,
    score: String,
    modifier: Modifier = Modifier,
    navigateToStartQuiz: () -> Unit,
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    if(openAlertDialog.value){
        if (quizHistories!!.isEmpty()){
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
        }else{
            AlertDialog(
                onDismissRequest = { openAlertDialog.value = false },
                title = {
                    Text(
                        text = "Kuis $name"
                    )
                },
                text = {
                    Column {
                        Text(
                            text = "Kamu sudah pernah melakukan kuis ini apakah kamu ingin tetap melanjutkan?",
                            modifier = modifier
                                .padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Score tertinggi kamu dapat kuis ini adalah $score"
                        )
                    }
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

//@Preview
//@Composable
//fun PreviewArticleItem(){
//    AmbatikTheme {
//        QuizItem(
//            name = "Batik Nusantara",
//            navigateToStartQuiz = {},
//            quizHistories = [],
//            score = ""
//        )
//    }
//}