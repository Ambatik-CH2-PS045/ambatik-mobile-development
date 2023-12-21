package com.example.ambatik.ui.screen.scan

import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.ambatik.BuildConfig
import com.example.ambatik.R
import com.example.ambatik.data.factory.BatikModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.alert.AlertLogin
import com.example.ambatik.ui.components.shop.ProductBatikItem
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.utlis.createCustomTempFile
import com.example.ambatik.utlis.uriToFile
import java.util.Objects
import java.util.logging.Handler

@Composable
fun ScanScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: ScanViewModel = viewModel(
        factory = BatikModelFactory.getInstance(LocalContext.current)
    ),
    navigateToWelcome: () -> Unit,
    navigateToDetailProduct: (Int) -> Unit,
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore),
    ){
    var scanBatikState by remember { mutableStateOf(false) }
    val statusState by viewModel.status.observeAsState(false)
    val hasilPredictBatik = viewModel.detailScanBatik.observeAsState()
    val akurasiBatik = viewModel.akurasiBatik.observeAsState().value
    val rekomendasiBatik = viewModel.rekomendasiProduk.observeAsState()
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

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess: Boolean ->
        if (isSuccess) {
            capturedImage = Uri.fromFile(file)
            hasImage = true
            Log.d("CameraURI", "URI from camera: $capturedImage")
        } else {
            Log.d("CameraURI", "Image capture failed.")
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri1 ->
        if (uri1 != null) {
            capturedImage = uriToFile(uri1, context).toUri()
            hasImage = true
        }
        Log.d("GalleryURI", "URI from gallery: $uri1")
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            hasImage = false
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val loading by viewModel.loading.observeAsState(initial = false)
    var checkImage by remember { mutableStateOf(false) }

    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))
    var alertLogin = remember { mutableStateOf(true) }

    if (!alertLogin.value) {
        AlertLogin(
            isLogin = alertLogin.value,
            navigateToWelcome = {
                navigateToWelcome()
            }
        )
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
                if (capturedImage?.path?.isNotEmpty() == true) {
                    checkImage = true
                    if (hasImage){
                        Image(
                            contentDescription = "Image Scan Batik",
                            painter = rememberImagePainter(capturedImage),
                            modifier = modifier
                                .size(300.dp, 400.dp)
                                .border(
                                    2.dp,
                                    color = colorScheme.onSurface,
                                    RoundedCornerShape(20.dp)
                                )
                        )
                    }else{
                        Image(
                            painter = painterResource(id = R.drawable.ambatik1),
                            contentDescription = "Image Scan Batik",
                            modifier = modifier
                                .size(300.dp, 400.dp)
                                .border(
                                    2.dp,
                                    color = colorScheme.onSurface,
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(16.dp)
                        )
                    }
                }
                else {
                    checkImage = false
                    Image(
                        painter = painterResource(id = R.drawable.ambatik1),
                        contentDescription = "Image Scan Batik",
                        modifier = modifier
                            .size(300.dp, 400.dp)
                            .border(2.dp, color = colorScheme.onSurface, RoundedCornerShape(20.dp))
                            .padding(16.dp)
                    )
                }
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
                                        hasImage = false
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
                                    if (userModel.isLogin){
                                        if (checkImage){
                                            viewModel.scanBatik(capturedImage.toFile())
                                            scanBatikState = true
                                        }else{
                                            Toast.makeText(context, "Silahkan pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show()
                                        }

                                    }else{
                                        alertLogin.value = userModel.isLogin
                                    }
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
                                    hasImage = false
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
            var stateAkurasi by remember { mutableStateOf(false) }
            LaunchedEffect(akurasiBatik?.accuracy) {
                android.os.Handler().postDelayed({
                    akurasiBatik?.accuracy?.let { accuracy ->
                        stateAkurasi = accuracy >= 80.0
                    }
                }, 250)
            }
            if (!loading){
                Log.d("DEBUG", "ScanScreen: ${akurasiBatik?.accuracy}")
                if (akurasiBatik?.accuracy != null && akurasiBatik.accuracy < 80.0){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = modifier
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = modifier
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "Mohon maaf kami tidak mengenali batik tersebut, mohon foto batik lebih jelas",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }else{
                    Log.d("DEBUG", "ScanScreen: ${akurasiBatik?.accuracy}")
                    var formatAkurasi by remember { mutableStateOf(0.0) }
                    LaunchedEffect(akurasiBatik?.accuracy){
                        android.os.Handler().postDelayed({
                            var akurasi = akurasiBatik?.accuracy

                            if (akurasi != null && akurasi.toInt() < 100) {
                                formatAkurasi = String.format("%.2f", akurasi).toDoubleOrNull() ?: 0.0
                            } else {
                                formatAkurasi = 100.0
                            }
                        }, 250)
                    }
                    if (!loading){
                        LazyColumn(
                            contentPadding = PaddingValues(bottom = 30.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = modifier
                                .padding(horizontal = 16.dp)
                        ){
                            item {
                                hasilPredictBatik.value?.let { data ->
                                    Column(
                                        modifier = modifier
                                            .padding(bottom = 12.dp)
                                    ) {
                                        AsyncImage(
                                            model = data.urlBatik,
                                            contentDescription = "Detail Scan Batik",
                                            contentScale = ContentScale.Crop,
                                            modifier = modifier
                                                .padding(bottom = 12.dp)
                                                .fillMaxWidth()
                                                .height(250.dp)
                                                .clip(
                                                    RoundedCornerShape(
                                                        bottomStart = 24.dp,
                                                        bottomEnd = 24.dp
                                                    )
                                                )
                                        )
                                        Box(
                                            modifier = modifier
                                                .padding(horizontal = 12.dp)
                                        ){
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
                                                        .padding(top = 4.dp)
                                                )
                                                Text(
                                                    text = "Akurasi pengecekan: $formatAkurasi",
                                                    modifier = modifier
                                                        .padding(top = 4.dp)
                                                )
                                                Divider(
                                                    modifier = modifier
                                                        .padding(top = 16.dp)
                                                        .height(0.75.dp)
                                                )
                                                Text(
                                                    text = "Arti",
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = modifier
                                                        .padding(top = 16.dp)
                                                )
                                                Text(
                                                    text = data.meaning ?: "",
                                                    textAlign = TextAlign.Justify
                                                )
                                                Divider(
                                                    modifier = modifier
                                                        .padding(top = 16.dp)
                                                        .height(0.75.dp)
                                                )
                                                Text(
                                                    text = "Proses Pembuatan",
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = modifier
                                                        .padding(top = 16.dp)
                                                )
                                                Text(
                                                    text = data.makingProcess ?: "",
                                                    textAlign = TextAlign.Justify
                                                )
                                                Divider(
                                                    modifier = modifier
                                                        .padding(top = 16.dp)
                                                        .height(0.75.dp)
                                                )
                                                Text(
                                                    text = "Penggunaan",
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = modifier
                                                        .padding(top = 16.dp)
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
                            item {
                                Text(
                                    text = "Rekomendasi Batik",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = modifier
                                        .padding(top = 16.dp)
                                )
                            }
                            item {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ){
                                    items(rekomendasiBatik.value ?: emptyList()){ data ->
                                        ProductBatikItem(
                                            image = data?.urlProduct ?: "",
                                            nameProduct = data?.name ?: "",
                                            price = data?.price.toString() ?: "",
                                            store = data?.storeName ?: "",
                                            rating = data?.rating?.toDouble() ?: 0.0,
                                            productSold = data?.productSold.toString() ?: "",
                                            modifier = modifier
                                                .clickable {
                                                    data?.id?.let { navigateToDetailProduct(it) }
                                                }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (loading){
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
fun PreviewScanScreen(){
    AmbatikTheme {
        ScanScreen(
            navigateToWelcome = {},
            navigateToDetailProduct = {}
        )
    }
}