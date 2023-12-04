package com.example.ambatik.ui.screen.editprofile

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.MaterialTheme.colorScheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
import coil.compose.AsyncImage
import com.example.ambatik.ui.screen.register.isValidEmail
import com.example.ambatik.ui.screen.register.isValidPassword
import com.example.ambatik.ui.theme.AmbatikTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    Surface(
        color = colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        var fullname by remember { mutableStateOf("") }
        var numberHandphone by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var passwordEditProfile by remember { mutableStateOf("") }
        var showPasswordEditProfile by remember { mutableStateOf(value = false) }

        var isValidEmpty by remember { mutableStateOf(true) }
        var isValidEmail by remember { mutableStateOf(true) }
        var isValidEmptyPassword by remember { mutableStateOf(true) }
        var isValidPassword by remember { mutableStateOf(true) }

        val localFocusManager = LocalFocusManager.current
        val context = LocalContext.current
        Column {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = modifier
                    .padding(20.dp, 50.dp, 20.dp, 0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "",
                        contentScale = ContentScale.Crop,
                        contentDescription = "Edit Profile",
                        modifier = modifier
                            .size(150.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = "Change Picture",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = colorScheme.onSurface,
                        modifier = modifier
                            .padding(top = 12.dp)
                            .clickable {

                            }
                    )
                }
            }
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = modifier
                    .padding(20.dp, 16.dp, 20.dp, 0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                Column {
                    OutlinedTextField(
                        value = fullname,
                        onValueChange = { fullname = it },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { localFocusManager.moveFocus(FocusDirection.Down) }
                        ),
                        singleLine = true,
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
                            focusedBorderColor = colorScheme.outline,
                            unfocusedBorderColor = colorScheme.outline,
                            containerColor = Color.White,
                            focusedTextColor = colorScheme.onSurface,
                            unfocusedTextColor = colorScheme.onSurface,
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(8.dp, 4.dp, 8.dp, 4.dp)
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { localFocusManager.moveFocus(FocusDirection.Down) }
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Address",
                                color = Color(0xFF86888D)
                            )
                        },
                        label = {
                            Text(
                                text = "Address",
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
                            .padding(8.dp, 4.dp, 8.dp, 4.dp)
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = {input ->
                            email = input
                            isValidEmpty = input.isNotEmpty()
                            isValidEmail = isValidEmailTextField(input)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { localFocusManager.moveFocus(FocusDirection.Down) }
                        ),
                        singleLine = true,
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
                            focusedBorderColor = colorScheme.outline,
                            unfocusedBorderColor = colorScheme.outline,
                            containerColor = Color.White,
                            focusedTextColor = colorScheme.onSurface,
                            unfocusedTextColor = colorScheme.onSurface,
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(8.dp, 4.dp, 8.dp, 4.dp)
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = numberHandphone,
                        onValueChange = { numberHandphone = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { localFocusManager.moveFocus(FocusDirection.Down) }
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "No Handphone",
                                color = Color(0xFF86888D)
                            )
                        },
                        label = {
                            Text(
                                text = "No Handphone",
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
                            .padding(8.dp, 4.dp, 8.dp, 4.dp)
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = passwordEditProfile,
                        onValueChange = { input ->
                            passwordEditProfile = input
                            isValidEmptyPassword = input.isNotEmpty()
                            isValidPassword = isValidPassword(input) },
                        visualTransformation = if (showPasswordEditProfile) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { localFocusManager.clearFocus() }
                        ),
                        singleLine = true,
                        trailingIcon = {
                            if(showPasswordEditProfile){
                                IconButton(onClick = { showPasswordEditProfile = false }) {
                                    Icon(
                                        imageVector = Icons.Filled.Visibility,
                                        contentDescription = "Hide Password",
                                        tint = colorScheme.onSurface
                                    )
                                }
                            } else{
                                IconButton(onClick = { showPasswordEditProfile = true }) {
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
                            .padding(8.dp, 4.dp, 8.dp, 4.dp)
                            .fillMaxWidth()
                    )
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(colorScheme.primary),
                        onClick = {
                            if(!isValidEmail || !isValidPassword){
                                Toast.makeText(context, "Masukan Format email dan password yang benar", Toast.LENGTH_SHORT).show()
                            }else{

                            }
                        },
                        modifier = Modifier
                            .padding(8.dp, 20.dp, 8.dp, 4.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                    ) {
                        Text(
                            text = "Sign up",
                            color = colorScheme.onPrimary,
                        )
                    }
                }
            }
        }
    }
}

fun isValidEmailTextField(text: String): Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(text).matches()
}

@Preview
@Composable
fun PreviewEditProfileScreen(){
    AmbatikTheme {
        EditProfileScreen()
    }
}