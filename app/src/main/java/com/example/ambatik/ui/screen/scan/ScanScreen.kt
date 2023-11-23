package com.example.ambatik.ui.screen.scan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.R
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun ScanScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Text(
                text = "Know Your Batik",
                color = Color.White,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .padding(50.dp)
                    .fillMaxWidth()
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Image Scan Batik",
                modifier = modifier
                    .size(300.dp, 400.dp)
                    .border(2.dp, color = Color.White, RoundedCornerShape(20.dp))
            )
            Box(
                modifier = modifier
                    .padding(0.dp, 25.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .height(500.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Take a picture or upload image to scan",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(0.dp, 20.dp)
                            .fillMaxWidth()
                    )
                    Row(
                        horizontalArrangement = Arrangement
                            .spacedBy(
                                space = 30.dp,
                                alignment = Alignment.CenterHorizontally
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        FloatingActionButton(
                            shape = CircleShape,
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(65.dp)
                                .alpha(0.7f),
                            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, 0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription =""
                            )
                        }
                        FloatingActionButton(
                            shape = CircleShape,
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(85.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.scanner),
                                contentDescription = "Scanner Batik",
                                modifier = modifier
                                    .padding(20.dp)
                                    .fillMaxSize()
                            )
                        }
                        FloatingActionButton(
                            shape = CircleShape,
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(65.dp)
                                .alpha(0.7f),
                            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, 0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PhotoLibrary,
                                contentDescription =""
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
fun PreviewScanScreen(){
    AmbatikTheme {
        ScanScreen()
    }
}