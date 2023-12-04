package com.example.ambatik.ui.screen.editprofile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun EditProfileScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {

    }
}

@Preview
@Composable
fun PreviewEditProfileScreem(){
    AmbatikTheme {
        EditProfileScreen()
    }
}