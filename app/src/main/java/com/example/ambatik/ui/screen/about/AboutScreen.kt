package com.example.ambatik.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambatik.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ambatik1),
                    contentDescription = "IMAGE AMBATIK SCREEN",
                    modifier = Modifier
                        .size(300.dp, 100.dp)
                )
            }
            Text(
                text = "About the application",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = "The goal is to address the decreasing interest in traditional Indonesian batik by creating an application that serves as an educational tool. This app aims to increase awareness among the Indonesian people about the significance of batik as an intangible cultural heritage recognized by UNESCO. Additionally, the application seeks to promote and support local batik small and medium enterprises (SMEs) by incorporating an e-commerce feature to facilitate business development in the batik industry. Ultimately, the objective is to preserve and promote Indonesian cultural identity through the revitalization of interest in traditional batik while simultaneously contributing to the economic growth of local batik businesses.",
                textAlign = TextAlign.Justify,
                modifier = modifier
                    .padding(vertical = 12.dp)
            )
            Text(
                text = "Feature",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = modifier
                    .padding(top = 4.dp)
            )
            Text(
                text = " - Authentication (Login & Register) \n - Show article and like article about batik \n - Learning material about types of batik \n - Recognize type of batik by scanning it directly \n - Quiz about batik \n - E-commerce \n - Personalization batik for user",
                textAlign = TextAlign.Justify,
                modifier = modifier
                    .padding(vertical = 12.dp)
            )

            Text(
                text = "Contributors",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = modifier
                    .padding(top = 4.dp)
            )
            Text(
                text = " - Gusti Ayu Wahyu Whurapsari \n - Ida Ayu Nityananda Shandra V Manuaba \n - Jonathan Ryan Darmawan \n - Ardel Vito Putra Herijanto \n - I Putu Martin Winata \n - Austin Lieandro \n - Roosyidah Alya Rakhman",
                textAlign = TextAlign.Justify,
                modifier = modifier
                    .padding(vertical = 12.dp)
            )
            Divider(
                modifier = modifier
                    .padding(start = 4.dp, end = 4.dp, top = 16.dp, bottom = 16.dp)
                    .height(0.75.dp)
            )
            Text(
                text = "If you find a bug in this application please contact: \nMachine Learning:\n - m014bsx0963@bangkit.academy\n - m014bsx1459@bangkit.academy\n - m320bsy1205@bangkit.academy\n \n Cloud Computing: \n - c014bsy4111@bangkit.academy\n - c320bsy3226@bangkit.academy\n \n Mobile Development:\n - a295bsx2031@bangkit.academy\n - a320bsy2034@bangkit.academy",
                modifier = modifier
                    .padding(top = 4.dp)
            )
        }
    }
}