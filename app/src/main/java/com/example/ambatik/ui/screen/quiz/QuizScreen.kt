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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ambatik.R
import com.example.ambatik.ui.components.quiz.QuizItem
import com.example.ambatik.ui.screen.editprofile.EditProfileScreen
import com.example.ambatik.ui.theme.AmbatikTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuizScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
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
                        model = "",
                        contentScale = ContentScale.Crop,
                        contentDescription = "Edit Profile",
                        modifier = modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = modifier
                            .padding(0.dp, 0.dp, 0.dp, 8.dp)
                            .width(120.dp)
                    ) {
                        Text(
                            text = "Welcome,",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            modifier  = modifier
                                .fillMaxWidth(),
                            text = "User",
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
                                .size(100.dp, 50.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .alpha(0.5f)
                        )
                        Box(
                            modifier = modifier
                                .padding(15.dp, 15.dp)
                                .size(100.dp),
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
                                    text = "99999",
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }

                    }
                }
            }
            Box(
                modifier = modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                ){
                    Column(
                        modifier = Modifier
                            .padding(bottom = 80.dp)
                    ){
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ){
//                            Card(
//                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
//                                modifier = modifier
//                                    .clickable {
//                                    }
//                            ){
//                                QuizItem(name = "Batik Nusantara", level = "Level Easy")
//                            }
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

