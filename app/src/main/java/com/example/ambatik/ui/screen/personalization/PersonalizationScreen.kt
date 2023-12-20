package com.example.ambatik.ui.screen.personalization

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ambatik.R
import com.example.ambatik.data.factory.PersonalizationFactory
import com.example.ambatik.ui.components.shop.ProductBatikItem
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.ui.theme.Shapes

@Composable
fun PersonalizationScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: PersonalizationViewModel = viewModel(
        factory = PersonalizationFactory.getInstance(LocalContext.current)
    ),
    navigateToDetailBatik: (Int) -> Unit
) {
    val rekomendasi = viewModel.rekomendasi.observeAsState()

    var pertanyaan by remember { mutableStateOf("") }
    var jawaban1 by remember { mutableStateOf("") }
    var jawaban2 by remember { mutableStateOf("") }
    var selectedAnswer by remember { mutableStateOf(mutableListOf<Int>()) }

    var statePertanyaan by remember { mutableStateOf(1) }
    var stateJawaban1 by remember { mutableStateOf(1) }
    var stateJawaban2 by remember { mutableStateOf(1) }
    var changeScreen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    when(statePertanyaan){
        1 -> pertanyaan = "Pilihlah preferensi warna batik Anda"
        2 -> pertanyaan = "Apakah Anda lebih suka motif batik"
        3 -> pertanyaan = "Untuk tujuan apa anda memilih motif batik"
    }

    when(stateJawaban1){
        1 -> jawaban1 = "Warna cerah dan kontras"
        2 -> jawaban1 = "Tradisional dan klasik"
        3 -> jawaban1 = "Sederhana untuk penggunaan sehari-hari"
    }

    when(stateJawaban2){
        1 -> jawaban2 = "Warna lembut dan netral"
        2 -> jawaban2 = "Modern dan inovatif"
        3 -> jawaban2 = "Untuk acara khusus seperti menghadiri undangan pernikahan, menghadiri pertemuan penting, dan lainnya"
    }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        if (statePertanyaan < 4){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Box(
                    modifier = modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Column {
                        Text(
                            text = pertanyaan,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier
                                .fillMaxWidth()
                        )
                        Button(
                            shape = RoundedCornerShape(15.dp),
                            onClick = {
                                statePertanyaan += 1
                                stateJawaban1 += 1
                                stateJawaban2 += 1
                                selectedAnswer.add(1)
                                Toast.makeText(context, "$statePertanyaan", Toast.LENGTH_SHORT).show()
                            },
                            modifier = modifier
                                .padding(vertical = 12.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = jawaban1)
                        }
                        Button(
                            shape = RoundedCornerShape(15.dp),
                            onClick = {
                                statePertanyaan += 1
                                stateJawaban1 += 1
                                stateJawaban2 += 1
                                selectedAnswer.add(2)
                            },
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            Text(text = jawaban2)
                        }
                    }
                }
            }
        }else{
            if(!changeScreen){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Kamu telah menyelesaikan pertanyaan personalisasi batik kamu. Jika kamu ingin melihat hasilnya tekan lanjutkan",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .padding(bottom = 12.dp)
                    )
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            viewModel.personalization(selectedAnswer)
                            changeScreen = true
                        },
                        modifier = modifier
                            .padding(bottom = 14.dp)
                            .height(45.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Hasil Personalisasi")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        onClick = {
                            navController.navigateUp()
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .border(
                                2.dp,
                                color = MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(10.dp)
                            )
                    ) {
                        Text(
                            text = "Kembali",
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }else{
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                ){
                    item {
                        Text(
                            text = "Berikut merupakan rekomendasi batik yang cocok buat kamu.",
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .padding(bottom = 12.dp)
                                .fillMaxWidth()
                        )
                    }
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ){
                            items(rekomendasi.value ?: emptyList()){ data ->
                                Card(
                                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
                                    modifier = modifier
                                        .size(150.dp, 200.dp)
                                        .clickable {
                                            data?.id?.let { navigateToDetailBatik(it) }
                                        }
                                ) {
                                    Column {
                                        AsyncImage(
                                            model = data?.urlImage,
                                            contentDescription = "PERSONALISASI BATIK",
                                            contentScale = ContentScale.Crop,
                                            modifier = modifier
                                                .size(150.dp, 150.dp)
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                                        )
                                        Text(
                                            text = data?.name ?: "",
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontSize = 16.sp,
                                            lineHeight = 16.sp,
                                            maxLines = 1,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = modifier
                                                .padding(12.dp, 8.dp, 12.dp, 0.dp)
                                                .fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
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
        PersonalizationScreen(
            navigateToDetailBatik = {}
        )
    }
}

