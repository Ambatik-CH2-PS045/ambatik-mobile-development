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
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambatik.api.response.QuizHistoriesItem
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.alert.AlertLogin

@Composable
fun QuizItem(
    name: String,
    quizHistories: List<QuizHistoriesItem?>?,
    score: String,
    modifier: Modifier = Modifier,
    navigateToStartQuiz: () -> Unit,
    navigateToWelcome: () -> Unit,
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore),
    ) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))
    var alertLogin = remember { mutableStateOf(true) }

    if (!alertLogin.value) {
        AlertLogin(
            isLogin = alertLogin.value,
            navigateToWelcome = {
                navigateToWelcome()
            }
        )
    }

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
                            text = "Kamu sudah pernah melakukan kuis ini apakah kamu ingin tetap melanjutkan?"
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

    if (userModel.isLogin){
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
        ){
            Row(
                modifier = modifier
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = modifier
                        .padding(16.dp, 4.dp)
                        .fillMaxWidth()
                        .height(100.dp)
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
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
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
                            fontSize = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Text(
                            text = "5 Soal",
                            modifier = modifier
                                .padding(vertical = 6.dp)
                        )
                        if (quizHistories!!.isEmpty()){
                            Text(text = "")
                        }else{
                            Text(
                                text = "High Score: $score",
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }else{
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
        ){
            Row(
                modifier = modifier
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = modifier
                        .padding(16.dp, 4.dp)
                        .fillMaxWidth()
                        .height(100.dp)
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
                                alertLogin.value = userModel.isLogin
                            },
                            modifier = modifier
                                .padding(16.dp, 16.dp, 0.dp, 16.dp)
                                .size(125.dp, 50.dp)
                        ) {
                            Text(
                                text = "Mulai",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
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
                            fontSize = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Text(
                            text = "5 Soal",
                            modifier = modifier
                                .padding(vertical = 6.dp)
                        )
                        if (quizHistories!!.isEmpty()){
                            Text(text = "")
                        }else{
                            Text(
                                text = "High Score: $score",
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}