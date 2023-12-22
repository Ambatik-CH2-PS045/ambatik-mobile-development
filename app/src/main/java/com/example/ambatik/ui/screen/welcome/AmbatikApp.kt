package com.example.ambatik.ui.screen.welcome

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.R
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.theme.AmbatikTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AmbatikApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
){
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    BackHandler(enabled = true) {
        scope.launch {
            (context as? Activity)?.finish()
        }
    }

    Surface(
        color = colorScheme.surface,
        modifier = modifier
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ambatik1),
                contentDescription = "Foto Login",
                modifier = Modifier
                    .size(300.dp, 100.dp)
            )
            Text(
                text = "Welcome to Ambatik",
                color = colorScheme.onSurface,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "Ambatik is an Android-based application aimed at increasing awareness, understanding, and accessibility of Indonesian batik.",
                color = colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 8.dp)
            )
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorScheme.primary),
                onClick = { navController.navigate(Screen.Login.route)},
                modifier = Modifier
                    .size(327.dp, 55.dp)
            ) {
                Text(
                    text = "Login",
                    color = colorScheme.onPrimary,
                    fontSize = 16.sp
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                onClick = { navController.navigate(Screen.Register.route) },
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
                    .size(327.dp, 55.dp)
                    .border(2.dp, color = colorScheme.primary, RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Register",
                    color = colorScheme.primary,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmbatikAppPreview(){
    AmbatikTheme {
        AmbatikApp()
    }
}