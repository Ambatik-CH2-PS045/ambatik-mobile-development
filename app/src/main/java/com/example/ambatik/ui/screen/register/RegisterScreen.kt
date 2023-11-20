package com.example.ambatik.ui.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambatik.ui.screen.login.LoginScreen
import com.example.ambatik.ui.theme.AmbatikTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
){
    Surface(
        color = Color(0xFF282A37),
        modifier = modifier
    ) {
        var fullname by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var passwordRegister by remember { mutableStateOf("") }
        var reTypePasswordRegister by remember { mutableStateOf("") }

        var showPasswordRegister by remember { mutableStateOf(value = false) }
        var showReTypePasswordRegister by remember { mutableStateOf(value = false) }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ){
            Text(
                text = "Sign up Account",
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
                value = fullname,
                onValueChange = { fullname = it },
                placeholder = {
                    Text(
                        text = "Fullname",
                        color = Color(0xFF86888D)
                    )
                },
                label = {
                    Text(
                        text = "Fullname",
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
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = {
                    Text(
                        text = "Email",
                        color = Color(0xFF86888D)
                    )
                },
                label = {
                    Text(
                        text = "Email",
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
                value = passwordRegister,
                onValueChange = { passwordRegister = it },
                visualTransformation = if (showPasswordRegister) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    if(showPasswordRegister){
                        IconButton(onClick = { showPasswordRegister = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "Hide Password",
                                tint = Color(0xFFFFFFFF)
                            )
                        }
                    } else{
                        IconButton(onClick = { showPasswordRegister = true }) {
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
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(327.dp)
            )
            OutlinedTextField(
                value = reTypePasswordRegister,
                onValueChange = { reTypePasswordRegister = it },
                visualTransformation = if (showReTypePasswordRegister) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    if(showReTypePasswordRegister){
                        IconButton(onClick = { showReTypePasswordRegister = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "Hide Password",
                                tint = Color(0xFFFFFFFF)
                            )
                        }
                    } else{
                        IconButton(onClick = { showReTypePasswordRegister = true }) {
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
                        text = "Re-Password",
                        color = Color(0xFF86888D)
                    )
                },
                label = {
                    Text(
                        text = "Re-Password",
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
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF1D90F4)),
                onClick = { /*Ke halaman Home dan kirim data*/ },
                modifier = Modifier
                    .size(327.dp, 55.dp)
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text = "Create Account",
                    color = Color(0xFFFFFFFF),
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen(){
    AmbatikTheme {
        RegisterScreen()
    }
}