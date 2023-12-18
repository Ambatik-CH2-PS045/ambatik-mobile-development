package com.example.ambatik.ui.screen.scan

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.ambatik.BuildConfig
import com.example.ambatik.R
import com.example.ambatik.data.factory.BatikModelFactory
//import com.example.ambatik.ui.screen.editprofile.createImageFile
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.utlis.createCustomTempFile
import com.example.ambatik.utlis.uriToFile
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun ScanScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: ScanViewModel = viewModel(
        factory = BatikModelFactory.getInstance(LocalContext.current)
    )
){
    var scanBatikState by remember { mutableStateOf(false) }
    val statusState by viewModel.status.observeAsState(false)
    val hasilPredictBatik = viewModel.detailScanBatik.observeAsState()
    val context = LocalContext.current
    val file = createCustomTempFile(context)
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    var capturedImage by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){
        capturedImage = Uri.fromFile(file)
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
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Surface(
        color = colorScheme.surface,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (!scanBatikState){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                Text(
                    text = "Know Your Batik",
                    color = colorScheme.onSurface,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(50.dp)
                        .fillMaxWidth()
                )
                ImageContent(
                    modifier = modifier,
                    capturedImageUri = capturedImage
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
                            color = colorScheme.onSurface,
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
                                containerColor = colorScheme.primary,
                                contentColor = colorScheme.onPrimary,
                                onClick = {
                                    val permissionCheckResult =
                                        ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
                                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                        cameraLauncher.launch(uri)
                                    } else {
                                        permissionLauncher.launch(android.Manifest.permission.CAMERA)
                                    }
                                },
                                modifier = Modifier
                                    .size(60.dp)
                                    .alpha(0.7f),
                                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, 0.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CameraAlt,
                                    contentDescription ="Capture Image From Camera"
                                )
                            }
                            FloatingActionButton(
                                shape = CircleShape,
                                containerColor = colorScheme.primary,
                                contentColor = colorScheme.onPrimary,
                                onClick = {
                                    viewModel.scanBatik(capturedImage.toFile())
                                    scanBatikState = true
                                },
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
                                containerColor = colorScheme.primary,
                                contentColor = colorScheme.onPrimary,
                                onClick = {
                                    galleryLauncher.launch("image/*")
                                },
                                modifier = Modifier
                                    .size(60.dp)
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
        }else{
            hasilPredictBatik.value?.let { data ->
                Column(
                    modifier = modifier
                        .padding(12.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = modifier
                            .padding(bottom = 8.dp)
                    ) {
                        AsyncImage(
                            model = data.urlBatik,
                            contentDescription = "Detail Scan Batik",
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .padding(end = 12.dp)
                                .size(125.dp)
                        )
                        Box(
                            modifier = modifier
                                .size(230.dp, 125.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Column {
                                Text(
                                    text = data.name ?: "",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = data.origin ?: "",
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = modifier
                                        .padding(top = 8.dp)
                                )
                            }
                        }
                    }
                    Text(
                        text = "Arti:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = data.meaning ?: "",
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = "Proses Pembuatan:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = data.makingProcess ?: "",
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = "Penggunaan:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = data.usagePurpose ?: "",
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

@Composable
fun ImageContent(
    modifier: Modifier = Modifier,
    capturedImageUri: Uri? = null
){
    if (capturedImageUri?.path?.isNotEmpty() == true) {
        Image(
            contentDescription = "Image Scan Batik",
            painter = rememberImagePainter(capturedImageUri),
            modifier = modifier
                .size(300.dp, 400.dp)
                .border(2.dp, color = colorScheme.onSurface, RoundedCornerShape(20.dp))
        )
        Log.d("ShowImage", "Image from URI: $capturedImageUri")
    }
    else {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Image Scan Batik",
            modifier = modifier
                .size(300.dp, 400.dp)
                .border(2.dp, color = colorScheme.onSurface, RoundedCornerShape(20.dp))
        )
    }
}

@Preview
@Composable
fun PreviewScanScreen(){
    AmbatikTheme {
        ScanScreen()
    }
}