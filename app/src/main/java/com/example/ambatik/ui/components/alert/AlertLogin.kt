package com.example.ambatik.ui.components.alert

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

@Composable
fun AlertLogin(
    isLogin: Boolean,
    navigateToWelcome: () -> Unit,
){
    val openAlertDialog = remember { mutableStateOf(!isLogin) }
    if (openAlertDialog.value){
        AlertDialog(
            onDismissRequest = { openAlertDialog.value = false },
            title = {
                Text(
                    text = "Warning"
                )
            },
            text = {
                Text(
                    text = "Kamu belum melakukan login, harap melakukan login"
                )
            },
            confirmButton = {
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        openAlertDialog.value = false
                        navigateToWelcome()
                    }
                ) {
                    Text(
                        text = "Login"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { openAlertDialog.value = false }
                ) {
                    Text(
                        text = "Kembali"
                    )
                }
            }
        )
    }
}