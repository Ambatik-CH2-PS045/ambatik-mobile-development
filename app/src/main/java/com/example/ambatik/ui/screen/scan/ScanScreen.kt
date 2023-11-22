package com.example.ambatik.ui.screen.scan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun ScanScreen(
    navController: NavHostController = rememberNavController(),
){
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Scan Screen")
    }
}

@Preview
@Composable
fun PreviewScanScreen(){
    AmbatikTheme {
        ScanScreen()
    }
}