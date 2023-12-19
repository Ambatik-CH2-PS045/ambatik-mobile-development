package com.example.ambatik.ui.screen.startquiz

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.data.factory.QuizModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun StartQuizScreen(
    navController: NavHostController = rememberNavController(),
    quizType: String,
    modifier: Modifier = Modifier,
    viewModel: StartQuizViewModel = viewModel(
        factory = QuizModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore)
) {
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))
    var idModul by remember { mutableStateOf(0) }
    var idQuestion by remember { mutableStateOf(0) }
    var numberQuestion by remember { mutableStateOf(1) }
    val questionState = viewModel.quizQuestion.observeAsState()
    val answerState = viewModel.quizAnswer.observeAsState()
    val submitQuiz = viewModel.submitQuizez.observeAsState()
    var selectedAnswers by remember { mutableStateOf(mutableListOf<Int>()) }
    var selectedQuestion by remember { mutableStateOf(mutableListOf<Int>()) }
    var changeScreen by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    
    var state by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        state = true
    }
    
    if (state){
        AlertDialogShow(
            navController = navController,
            openAlertDialog = true
        )
    }

    when(quizType){
        "origin" -> idModul = 1
        "pattern" -> idModul = 2
        "meaning" -> idModul = 3
    }

    when(idModul){
        1 -> idQuestion = 1
        2 -> idQuestion = 6
        3 -> idQuestion = 11
    }

    LaunchedEffect(Unit){
        viewModel.getQuestion(idModul, idQuestion)
    }


    Surface(
        color = colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ){
        if (numberQuestion <= 5){
            questionState.value?.let { data ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Box(
                        modifier = modifier
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Pertanyaan $numberQuestion/5",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier
                                .fillMaxWidth()
                        )
                    }
                    Text(
                        text = data.question ?: "",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Justify
                    )
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        items(answerState.value ?: emptyList()){ dataAnswer ->
                            Button(
                                shape = RoundedCornerShape(10.dp),
                                onClick = {
                                    selectedAnswers.add(dataAnswer?.id ?: 0)
                                    selectedQuestion.add(dataAnswer?.questionId ?: 0)
                                    idQuestion += 1
                                    numberQuestion += 1
                                    viewModel.getQuestion(idModul, idQuestion)
                                    Log.d("TESTING LIST", "$selectedAnswers, $selectedQuestion")
                                },
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = dataAnswer?.answer ?: ""
                                )
                            }
                        }
                    }
                }
            }
        }else{
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ){
                if (!changeScreen){
                    Text(
                        text = "Kamu teleh menyelesaikan quiz kamu. Apakah kamu ingin melihat hasil quiz kamu?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .padding(bottom = 12.dp)
                    )
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            viewModel.submitQuiz(userModel.id, idModul, selectedQuestion, selectedAnswers)
                            changeScreen = true
                        },
                        modifier = modifier
                            .padding(bottom = 14.dp)
                            .height(45.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Hasil quiz")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        onClick = {
                            state = true
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(2.dp, color = colorScheme.primary, RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            text = "Kembali",
                            color = colorScheme.primary,
                        )
                    }
                } else{
                    submitQuiz.value?.let { dataSubmit ->
                        Card(
                            colors = CardDefaults.cardColors(colorScheme.onPrimary)
                        ) {
                            Box(
                                modifier = modifier
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column {
                                    Text(
                                        text = "Selamat kamu telah menyelesaikan quiz kamu.",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                    )
                                    Text(
                                        text = "Total jawaban benar: ${dataSubmit.totalCorrect}",
                                        textAlign = TextAlign.Center,
                                        modifier = modifier
                                            .padding(top = 8.dp)
                                            .fillMaxWidth()
                                    )
                                    Text(
                                        text = "Total jawaban salah: ${dataSubmit.totalWrong}",
                                        textAlign = TextAlign.Center,
                                        modifier = modifier
                                            .fillMaxWidth()
                                    )
                                    Text(
                                        text = "Total poin yang didapatkan: ${dataSubmit.accumulatePoint}",
                                        textAlign = TextAlign.Center,
                                        modifier = modifier
                                            .fillMaxWidth()
                                    )
                                    Button(
                                        shape = RoundedCornerShape(10.dp),
                                        onClick = { navController.navigateUp() },
                                        modifier = modifier
                                            .padding(top = 8.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Kembali"
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDialogShow(
    navController: NavHostController,
    openAlertDialog: Boolean = false,
    modifier: Modifier = Modifier
){
    val openAlertDialog = remember { mutableStateOf(openAlertDialog) }
    if(openAlertDialog.value){
        AlertDialog(
            onDismissRequest = { openAlertDialog.value = false },
            title = {
                Text(
                    text = "Halaman quiz"
                )
            },
            text = {
                Text(
                    text = "Apakah ingin kembali ke halaman quiz? data quiz kamu tidak akan tersimpan"
                )
            },
            confirmButton = {
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        navController.navigateUp()
                        openAlertDialog.value = false
                    }
                ) {
                    Text(
                        text = "Iya"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { openAlertDialog.value = false }
                ) {
                    Text(
                        text = "Tidak"
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewStartQuizScreen(){
    AmbatikTheme {
        StartQuizScreen(
            quizType = ""
        )
    }
}