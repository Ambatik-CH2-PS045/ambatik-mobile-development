package com.example.ambatik.ui.screen.quiz

import android.annotation.SuppressLint
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ambatik.R
import com.example.ambatik.data.factory.QuizModelFactory
import com.example.ambatik.data.factory.UserModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.quiz.QuizItem
import com.example.ambatik.ui.screen.editprofile.EditProfileScreen
import com.example.ambatik.ui.screen.profile.ProfileViewModel
import com.example.ambatik.ui.theme.AmbatikTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuizScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = viewModel(
        factory = QuizModelFactory.getInstance(LocalContext.current)
    ),
    viewModelProfile: ProfileViewModel = viewModel(
        factory = UserModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore)
){
    val listQuizState = viewModel.quizList.observeAsState()
    val detailUserState = viewModelProfile.detailUser.observeAsState()
    val statusState by viewModel.status.observeAsState(false)
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))

    LaunchedEffect(userModel.id){
        viewModel.getQuiz(userModel.id)
        viewModelProfile.getDetailUser(userModel.id)
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
        detailUserState.value?.let {data ->
            Column {
                Box(
                    modifier = modifier
                        .height(100.dp)
                        .padding(16.dp, 16.dp, 16.dp, 16.dp)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                    ) {
                        AsyncImage(
                            model = data.urlProfile,
                            contentScale = ContentScale.Crop,
                            contentDescription = "Edit Profile",
                            modifier = modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                        Column(
                            modifier = modifier
                                .padding(0.dp, 8.dp, 0.dp, 8.dp)
                                .width(120.dp)
                        ) {
                            Text(
                                text = "Welcome,",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                modifier  = modifier
                                    .fillMaxWidth(),
                                text = data.username ?: "",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                        Box(
                            modifier = modifier
                                .height(250.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Box(
                                modifier = modifier
                                    .padding(15.dp, 15.dp)
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(MaterialTheme.colorScheme.primary)
                            ){
                                Box(
                                    modifier = modifier
                                        .size(75.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row {
                                        Icon(
                                            imageVector = Icons.Filled.Stars,
                                            contentDescription = "Score",
                                            tint = MaterialTheme.colorScheme.onPrimary,
                                            modifier = modifier
                                                .padding(end = 4.dp)
                                        )
                                        Text(
                                            text = data.point.toString(),
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                ){
                    Box(
                        modifier = Modifier
                            .padding(bottom = 80.dp)
                    ){
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ){
                            items(listQuizState.value ?: emptyList()){data ->
                                Card(
                                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
                                ){
                                    QuizItem(
                                        name = data?.type ?: ""
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

@Preview
@Composable
fun PreviewEditQuizScreen(){
    AmbatikTheme {
        QuizScreen()
    }
}

