package com.example.ambatik.ui.screen.editprofile

import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.ambatik.BuildConfig
import com.example.ambatik.data.factory.UserModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.screen.profile.ProfileViewModel
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.utlis.createCustomTempFile
import com.example.ambatik.utlis.uriToFile
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = viewModel(
        factory = UserModelFactory.getInstance(LocalContext.current)
    ),
    editViewModel: EditProfileViewModel = viewModel(
        factory = UserModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore)
){
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val file = createCustomTempFile(context)
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )
    var hasImage by remember {
        mutableStateOf(false)
    }

    var capturedImage by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){
        capturedImage = Uri.fromFile(file)
        hasImage = true
        Log.d("CameraURI", "URI from camera: $uri")
    }
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri1 ->
        if (uri1 != null) {
            capturedImage = uriToFile(uri1, context).toUri()
        }
        Log.d("GalleryURI", "URI from gallery: $uri1")
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
            hasImage = false
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val loadingEditProfile by editViewModel.loadingEditProfile.observeAsState(false)
    val loadingEditPhotoProfile by editViewModel.loadingEditPhotoProfile.observeAsState(false)
    var checkImage by remember { mutableStateOf(false) }

    val localFocusManager = LocalFocusManager.current

    val detailUserState = profileViewModel.detailUser.observeAsState()
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))

    LaunchedEffect(userModel.id){
        profileViewModel.getDetailUser(userModel.id)
    }

    Surface(
        color = colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        detailUserState.value?.let { data ->
            var fullname by remember { mutableStateOf(data.name) }
            var numberHandphone by remember { mutableStateOf(data.phone) }
            var address by remember { mutableStateOf(data.address) }
            Column {
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = modifier
                        .padding(20.dp, 50.dp, 20.dp, 0.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (capturedImage?.path?.isNotEmpty() == true) {
                            checkImage= true
                            if (hasImage){
                                Image(
                                    painter = rememberImagePainter(capturedImage),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Edit Profile",
                                    modifier = modifier
                                        .size(150.dp)
                                        .clip(CircleShape)
                                )
                            }
                        } else {
                            checkImage = false
                            AsyncImage(
                                model = data.urlProfile,
                                contentScale = ContentScale.Crop,
                                contentDescription = "Edit Profile",
                                modifier = modifier
                                    .size(150.dp)
                                    .clip(CircleShape)
                            )
                        }
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
                ) {
                    Column {
                        OutlinedTextField(
                            value = fullname ?: "",
                            onValueChange = { fullname = it },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { localFocusManager.moveFocus(FocusDirection.Down) }
                            ),
                            singleLine = true,
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
                            value = address ?: "",
                            onValueChange = { address = it },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { localFocusManager.moveFocus(FocusDirection.Down) }
                            ),
                            singleLine = true,
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
                            value = numberHandphone ?: "",
                            onValueChange = { numberHandphone = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { localFocusManager.moveFocus(FocusDirection.Down) }
                            ),
                            singleLine = true,
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
                        Button(
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(colorScheme.primary),
                            onClick = {
                                if (checkImage){
                                    editViewModel.editProfile(userModel.id, fullname ?: "", address ?: "", numberHandphone ?: "")
                                    editViewModel.editPhotoProfile(capturedImage.toFile(), userModel.id)
                                }else{
                                    editViewModel.editProfile(userModel.id, fullname ?: "", address ?: "", numberHandphone ?: "")
                                }
                            },
                            modifier = Modifier
                                .padding(8.dp, 20.dp, 8.dp, 4.dp)
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Change Profile",
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
                                            hasImage = false
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
        if (loadingEditProfile || loadingEditPhotoProfile){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewEditProfileScreen(){
    AmbatikTheme {
        EditProfileScreen()
    }
}