package com.example.ambatik.ui.screen.personalization

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.R
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.ui.theme.Shapes

@Composable
fun PersonalizationScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = modifier
                        .padding(16.dp, 16.dp, 16.dp, 16.dp)
                ){
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "Image Personalization",
                        modifier = modifier
                            .padding(8.dp)
                            .size(320.dp)
                            .clip(Shapes.medium)
                    )
                }
                Box(
                    modifier = modifier
                        .padding(16.dp, 0.dp, 16.dp, 8.dp)
                        .width(240.dp)
                ){
                    Column {
                        Text(
                            text = "Discover Your Batik",
                            maxLines = 2,
                            fontSize = 32.sp,
                            lineHeight = 34.sp,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Text(
                            text = "Find the batik that resonates with your personality",
                            maxLines = 2,
                            lineHeight = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = modifier
                                .padding(top = 16.dp)
                        )
                    }
                }
                Box(
                    modifier = modifier
                        .padding(16.dp, 16.dp, 16.dp, 16.dp)
                        .width(240.dp)
                ){
                    Button(
                        onClick = {
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        modifier = modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Text(
                            text = "Mulai",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewPersonalizationScreen(){
    AmbatikTheme {
        PersonalizationScreen()
    }
}

