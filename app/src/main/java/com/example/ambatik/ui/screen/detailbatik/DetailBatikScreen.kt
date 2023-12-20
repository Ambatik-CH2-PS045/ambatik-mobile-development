package com.example.ambatik.ui.screen.detailbatik

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ambatik.data.factory.BatikModelFactory
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun DetailBatikScreen(
    idBatik: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailBatikViewModel = viewModel(
        factory = BatikModelFactory.getInstance(LocalContext.current)
    )
) {
    val detailBatikState = viewModel.detailBatik.observeAsState()

    LaunchedEffect(Unit){
        viewModel.getDetailBatik(idBatik)
    }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            detailBatikState.value?.let { data ->
                DetailBatikContent(
                    image = data.urlBatik ?: "",
                    name = data.name ?: "",
                    origin = data.origin ?: "",
                    meaning = data.meaning ?: "",
                    makingProcess = data.makingProcess ?: "",
                    usagePurpose = data.usagePurpose ?: ""
                )
            }
        }
    }
}

@Composable
fun DetailBatikContent(
    image: String,
    name: String,
    origin: String,
    meaning: String,
    makingProcess: String,
    usagePurpose: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(bottom = 12.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Detail Batik Image",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(bottom = 12.dp)
                .height(250.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
        )
        Box(
            modifier = modifier
                .padding(horizontal = 12.dp)
        ) {
            Column {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = origin,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
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
                    text = meaning,
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
                    text = makingProcess,
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
                    text = usagePurpose,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailBatikContent(){
    AmbatikTheme {
        DetailBatikContent(
            image = "",
            name = "Batik Truntum",
            origin = "Daerah Istimewa Yogyakarta",
            meaning = "Motif batik ini diciptakan oleh Kanjeng Ratu Kencana (Permaisuri Ingkang Sinuhun Sri Susuhunan Pakubuwana III dari Surakarta). Motif batik ini bermakna cinta yang tumbuh kembali. Ia menciptakan motif ini sebagai simbol cinta yang tulus tanpa syarat, abadi, dan semakin lama semakin terasa subur berkembang (tumaruntum).",
            makingProcess = "Molani: Membuat pola pada bahan yang akan digunakan untuk membatik. Pola ini mungkin disebut kanjengan -Menulis tangan: Setelah molani selesai, tangan penulis digunakan untuk menulis nama pemilik atau motif yang ingin dicatat pada batik -Mbabar: Pewarnaan batik truntum dilakukan dengan menggunakan cara mbabar, yang melibatkan memperawatkan bahan dengan lain selama proses pembatikan",
            usagePurpose = "Kain bermotif truntum biasa dipakai oleh orang tua pengantin pada hari pernikahan. Ada alasan mengapa orangtua pengantin memakai batik motif truntum. Orangtua yang menggunakan batik truntum ini diharapkan agar bisa menuntun dan memberi contoh kepada pengantin saat akan memasuki kehidupan rumah tangga."
        )
    }
}