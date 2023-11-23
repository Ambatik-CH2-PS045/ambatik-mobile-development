package com.example.ambatik.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambatik.R
import com.example.ambatik.ui.theme.AmbatikTheme
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
){
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Text(
                text = "Profile",
                color = Color.White,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 30.dp)
                    .fillMaxWidth()
            )
            Box{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(100.dp, 100.dp)
                    )
                    Text(
                        text = "Full Name",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    Text(
                        text = "username",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = modifier
                            .padding(0.dp, 0.dp, 0.dp, 10.dp)
                    )
                }
            }
            Divider(
                modifier = modifier
                    .padding(15.dp, 5.dp, 15.dp, 0.dp),
                color = Color.White
            )
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
                            tint = Color(0xFFFFFFFF),
                            modifier = modifier
                                .padding(0.dp, 10.dp)
                        )
                        Text(
                            text = "Edit Profile",
                            color = Color(0xFFFFFFFF),
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
                                tint = Color(0xFFFFFFFF),
                            )
                        }
                    }
                    Row(
                        modifier = modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .clickable {
                                Toast
                                    .makeText(context, "Acticel", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Bookmark,
                            contentDescription = "Icon Bookmark Articel",
                            tint = Color(0xFFFFFFFF),
                            modifier = modifier
                                .padding(0.dp, 10.dp)
                        )
                        Text(
                            text = "Bookmark Articel",
                            color = Color(0xFFFFFFFF),
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
                                contentDescription = "Navigate to Bookmark Articel",
                                tint = Color(0xFFFFFFFF),
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
                            tint = Color(0xFFFFFFFF),
                            modifier = modifier
                                .padding(0.dp, 10.dp)
                        )
                        Text(
                            text = "Bookmark Product",
                            color = Color(0xFFFFFFFF),
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
                                tint = Color(0xFFFFFFFF),
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
                            tint = Color(0xFFFFFFFF),
                            modifier = modifier
                                .padding(0.dp, 10.dp)
                        )
                        Text(
                            text = "About",
                            color = Color(0xFFFFFFFF),
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
                                tint = Color(0xFFFFFFFF),
                            )
                        }
                    }
                }
            }
            Divider(
                modifier = modifier
                    .padding(15.dp, 0.dp, 15.dp, 5.dp),
                color = Color.White
            )
            Box(
                modifier = modifier
                    .padding(20.dp, 25.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.BottomEnd
            ) {
                OutlinedButton(
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, Color.Red),
                    onClick = { /*TODO*/ },
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Logout",
                        color = Color.Red
                    )
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