package com.example.ambatik.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.theme.AmbatikTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    var username by remember { mutableStateOf("") }
    var passwordLogin by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }

    val localFocusManager = LocalFocusManager.current
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
//                .fillMaxSize()
        ) {
            Text(
                text = "Login To Your Account",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Lorem ipsum dolor sit amet",
                color = Color(0xFF79869F),
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
                    focusedBorderColor = Color(0xFF323645),
                    unfocusedBorderColor = Color(0xFF323645),
                    containerColor = Color(0xFF323645),
                    focusedTextColor = Color(0xFFFFFFFF),
                    unfocusedTextColor = Color(0xFFFFFFFF),
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
                                tint = Color(0xFFFFFFFF)
                            )
                        }
                    } else{
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "Hide Password",
                                tint = Color(0xFFFFFFFF)
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
                    focusedBorderColor = Color(0xFF323645),
                    unfocusedBorderColor = Color(0xFF323645),
                    containerColor = Color(0xFF323645),
                    focusedTextColor = Color(0xFFFFFFFF),
                    unfocusedTextColor = Color(0xFFFFFFFF),
                ),
                isError = passwordLogin.isNotEmpty() && !isValidPassword(
                    passwordLogin
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(327.dp)
                    .padding(0.dp, 8.dp)
            )
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF1D90F4)),
                onClick = { navController.navigate(Screen.Home.route) },
                modifier = Modifier
                    .size(327.dp, 55.dp)
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text = "Login",
                    color = Color(0xFFFFFFFF),
                )
            }
        }
    }
}

fun isValidPassword(text: String): Boolean{
    return text.matches(Regex(".{8,}"))
}

@Preview
@Composable
fun PreviewLoginScreen(){
    AmbatikTheme {
        LoginScreen()
    }
}