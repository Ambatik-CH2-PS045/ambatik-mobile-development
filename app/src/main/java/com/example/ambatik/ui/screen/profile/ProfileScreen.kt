package com.example.ambatik.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.R
import com.example.ambatik.data.factory.UserModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.theme.AmbatikTheme
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

@Composable
fun ProfileScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(
        factory = UserModelFactory.getInstance(LocalContext.current)
    ),
){
    val statusState by viewModel.status.observeAsState(false)
    Surface(
        color = colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Box{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = modifier
                            .padding(20.dp, 20.dp, 0.dp, 0.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(100.dp, 100.dp)
                        )
                        Column {
                            Text(
                                text = "Full Name",
                                color = colorScheme.onSurface,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = modifier
                                    .padding(bottom = 8.dp)
                            )
                            Text(
                                text = "username",
                                color = colorScheme.onSurface,
                                fontSize = 18.sp,
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .padding(20.dp, 10.dp)
                    .fillMaxWidth()
            ) {
                val context = LocalContext.current
                Column{
                    Row(
                        modifier = modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .clickable {
                                Toast
                                    .makeText(context, "Edit Profile", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Icon Edit Profile",
                            tint = colorScheme.onSurface,
                            modifier = modifier
                                .padding(0.dp, 10.dp)
                        )
                        Text(
                            text = "Edit Profile",
                            color = colorScheme.onSurface,
                            modifier = modifier
                                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        )
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Icon(
                                imageVector = Icons.Filled.NavigateNext,
                                contentDescription = "Navigate to Edit Profile",
                                tint = colorScheme.onSurface,
                            )
                        }
                    }
                    Row(
                        modifier = modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(Screen.LikeArticle.route)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Article,
                            contentDescription = "Icon Bookmark Article",
                            tint = colorScheme.onSurface,
                            modifier = modifier
                                .padding(0.dp, 10.dp)
                        )
                        Text(
                            text = "Like Article",
                            color = colorScheme.onSurface,
                            modifier = modifier
                                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        )
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Icon(
                                imageVector = Icons.Filled.NavigateNext,
                                contentDescription = "Navigate to Bookmark Article",
                                tint = colorScheme.onSurface,
                            )
                        }
                    }
                    Row(
                        modifier = modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .clickable {
                                Toast
                                    .makeText(context, "Product", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Bookmarks,
                            contentDescription = "Icon Bookmark Product",
                            tint = colorScheme.onSurface,
                            modifier = modifier
                                .padding(0.dp, 10.dp)
                        )
                        Text(
                            text = "Bookmark Product",
                            color = colorScheme.onSurface,
                            modifier = modifier
                                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        )
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Icon(
                                imageVector = Icons.Filled.NavigateNext,
                                contentDescription = "Navigate to Bookmark Product",
                                tint = colorScheme.onSurface,
                            )
                        }
                    }
                    Row(
                        modifier = modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .clickable {
                                Toast
                                    .makeText(context, "About", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Icon About",
                            tint = colorScheme.onSurface,
                            modifier = modifier
                                .padding(0.dp, 10.dp)
                        )
                        Text(
                            text = "About",
                            color = colorScheme.onSurface,
                            modifier = modifier
                                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                        )
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Icon(
                                imageVector = Icons.Filled.NavigateNext,
                                contentDescription = "Navigate to About",
                                tint = colorScheme.onSurface,
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .padding(20.dp, 25.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = {
                        viewModel.logout()
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.120f)
                        .border(2.dp, color = colorScheme.primary, RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = "Logout",
                        color = colorScheme.primary
                    )
                }
                LaunchedEffect(statusState){
                    if (statusState){
                        navController.previousBackStackEntry?.arguments?.putBoolean("logout", true)
                        navController.popBackStack()
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen(){
    AmbatikTheme {
        ProfileScreen()
    }
}