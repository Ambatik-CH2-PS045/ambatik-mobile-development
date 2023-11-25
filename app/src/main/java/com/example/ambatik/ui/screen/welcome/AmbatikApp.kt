package com.example.ambatik.ui.screen.welcome

import android.annotation.SuppressLint
import android.app.Activity
import android.content.SharedPreferences
import android.os.Build
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.R
import com.example.ambatik.data.factory.UserModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.screen.login.LoginViewModel
import com.example.ambatik.ui.theme.AmbatikTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AmbatikApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: WelcomeViewModel = viewModel(
        factory = UserModelFactory.getInstance(LocalContext.current)
    ),
){
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    BackHandler(enabled = true) {
        scope.launch {
            (context as? Activity)?.finish()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    viewModel.getSession().observe(lifecycleOwner) { session ->
        if (session.isLogin){
            navController.navigate(Screen.Home.route)
        }
    }
    Surface(
        color = Color(0xFF282A37),
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
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Foto Login",
                modifier = Modifier
                    .size(250.dp, 250.dp)
            )
            Text(
                text = "Selamat datang di Ambatik",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ipsum orci, aliquam non leo non, laoreet blandit sem. Nunc nec.",
                color = Color(0xFF79869F),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 8.dp)
            )
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF1D90F4)),
                onClick = { navController.navigate(Screen.Login.route)},
                modifier = Modifier
                    .size(327.dp, 55.dp)
            ) {
                Text(text = "Login")
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF282A37)),
                onClick = { navController.navigate(Screen.Register.route) },
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
                    .size(327.dp, 55.dp)
                    .border(2.dp, color = Color(0xFF1D90F4), RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Register",
                    color = Color(0xFF1D90F4),
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