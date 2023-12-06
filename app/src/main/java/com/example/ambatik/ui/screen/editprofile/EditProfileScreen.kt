package com.example.ambatik.ui.screen.editprofile

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.ambatik.BuildConfig
import com.example.ambatik.R
import com.example.ambatik.ui.screen.register.isValidEmail
import com.example.ambatik.ui.screen.register.isValidPassword
import com.example.ambatik.ui.theme.AmbatikTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

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
        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }

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

        val context = LocalContext.current
        val file = context.createImageFile()
        val uri = FileProvider.getUriForFile(
            Objects.requireNonNull(context),
            BuildConfig.APPLICATION_ID + ".provider", file
        )
        var capturedImage by remember {
            mutableStateOf<Uri>(Uri.EMPTY)
        }
        val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){
            capturedImage = uri
            Log.d("CameraURI", "URI from camera: $uri")
        }
        val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri1 ->
            if (uri1 != null) {
                capturedImage = uri1
            }
            Log.d("GalleryURI", "URI from gallery: $uri1")
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

        val localFocusManager = LocalFocusManager.current
//        val context = LocalContext.current
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
                    ImageContent(
                        modifier = modifier,
                        capturedImageUri = capturedImage
                    )
//                    AsyncImage(
//                        model = ImageRequest.Builder(LocalContext.current)
//                            .data(capturedImage)
//                            .placeholder(R.drawable.baseline_account_circle_24)
//                            .build(),
//                        contentScale = ContentScale.Crop,
//                        contentDescription = "Edit Profile",
//                        modifier = modifier
//                            .size(150.dp)
//                            .clip(CircleShape)
//                    )
                    Text(
                        text = "Change Picture",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = colorScheme.onSurface,
                        modifier = modifier
                            .padding(top = 12.dp)
                            .clickable {
                                showBottomSheet = true
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
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    Box(
                        contentAlignment = Alignment.TopCenter,
                        modifier = modifier
                            .padding(8.dp, 8.dp, 8.dp, 8.dp)
                            .fillMaxWidth()
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Spacer(modifier = modifier.height(5.dp))
                            Button(
                                onClick = {
                                    val permissionCheckResult =
                                        ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
                                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                        cameraLauncher.launch(uri)
                                        showBottomSheet = false
                                    } else {
                                        permissionLauncher.launch(android.Manifest.permission.CAMERA)
                                    }
                                },
                                modifier = modifier
                                    .padding(16.dp, 16.dp, 16.dp, 16.dp)
                                    .width(140.dp)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = "Camera",
                                    color = colorScheme.onPrimary,
                                )
                            }
                            Button(
                                onClick = {
                                    galleryLauncher.launch("image/*")
                                    showBottomSheet = false
                                },
                                modifier = modifier
                                    .padding(16.dp, 0.dp, 16.dp, 16.dp)
                                    .width(140.dp)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = "Gallery",
                                    color = colorScheme.onPrimary,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun isValidEmailTextField(text: String): Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(text).matches()
}

@Composable
fun ImageContent(
    modifier: Modifier = Modifier,
    capturedImageUri: Uri? = null
){
    if (capturedImageUri?.path?.isNotEmpty() == true) {
        Image(
            painter = rememberImagePainter(capturedImageUri),
            contentScale = ContentScale.Crop,
            contentDescription = "Edit Profile",
            modifier = modifier
                .size(150.dp)
                .clip(CircleShape)
        )
        Log.d("ShowImage", "Image from URI: $capturedImageUri")
    } else {
        Image(
            painter = painterResource(id = R.drawable.baseline_account_circle_24),
            contentScale = ContentScale.Crop,
            contentDescription = "Edit Profile",
            modifier = modifier
                .size(150.dp)
                .clip(CircleShape)
        )
    }
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return image
}

@Preview
@Composable
fun PreviewEditProfileScreen(){
    AmbatikTheme {
        EditProfileScreen()
    }
}