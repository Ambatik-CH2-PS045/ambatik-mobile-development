package com.example.ambatik.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.data.factory.UserModelFactory
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.theme.AmbatikTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(
        factory = UserModelFactory.getInstance(LocalContext.current)
    )
){
    var username by remember { mutableStateOf("") }
    var passwordLogin by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }

    val localFocusManager = LocalFocusManager.current

    val context = LocalContext.current
    val statusState by viewModel.status.observeAsState(false)
    val errorState by viewModel.error.observeAsState(null)

    Surface(
        color = colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Text(
                text = "Login To Your Account",
                color = colorScheme.onSurface,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Lorem ipsum dolor sit amet",
                color = colorScheme.onSurface,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { localFocusManager.moveFocus(FocusDirection.Down) }
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Username",
                        color = Color(0xFF86888D)
                    )
                },
                label = {
                    Text(
                        text = "Username",
                        color = Color(0xFF86888D)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorScheme.outline,
                    unfocusedBorderColor = colorScheme.outline,
                    containerColor = Color.White,
                    focusedTextColor = colorScheme.onSurface,
                    unfocusedTextColor = colorScheme.onSurface,
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(327.dp)
            )
            OutlinedTextField(
                value = passwordLogin,
                onValueChange = { passwordLogin = it },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { localFocusManager.clearFocus() }
                ),
                singleLine = true,
                trailingIcon = {
                    if(showPassword){
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "Hide Password",
                                tint = colorScheme.onSurface
                            )
                        }
                    } else{
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "Hide Password",
                                tint = colorScheme.onSurface
                            )
                        }
                    }
                },
                placeholder = {
                    Text(
                        text = "Password",
                        color = Color(0xFF86888D)
                    )
                },
                label = {
                    Text(
                        text = "Password",
                        color = Color(0xFF86888D)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorScheme.outline,
                    unfocusedBorderColor = colorScheme.outline,
                    containerColor = Color.White,
                    focusedTextColor = colorScheme.onSurface,
                    unfocusedTextColor = colorScheme.onSurface,
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(327.dp)
                    .padding(0.dp, 8.dp)
            )
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorScheme.primary),
                onClick = {
                    viewModel.login(username, passwordLogin)
                },
                modifier = Modifier
                    .size(327.dp, 55.dp)
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text = "Login",
                    color = colorScheme.onPrimary,
                )
            }
            LaunchedEffect(statusState) {
                if (statusState) {
                    navController.navigate(Screen.Home.route)
                }
            }
            errorState?.let { errorMsg ->
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
        Box(
            contentAlignment = Alignment.Center,
        ){
            if (viewModel.loading.value == true){
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen(){
    AmbatikTheme {
        LoginScreen()
    }
}