package com.example.ambatik.ui.screen.articel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.R
import com.example.ambatik.data.factory.ArticleModelFactory
import com.example.ambatik.data.factory.BatikModelFactory
import com.example.ambatik.ui.components.article.ArticleItem
import com.example.ambatik.ui.components.batik.BatikItem
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun ArticelScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: ArticleViewModel = viewModel(
        factory = ArticleModelFactory.getInstance(LocalContext.current)
    ),
    viewModelBatik: BatikViewModel = viewModel(
        factory = BatikModelFactory.getInstance(LocalContext.current)
    ),
    navigateToDetail: (Int) -> Unit,
    navigateToDetailBatik: (Int) -> Unit
){
    val articleListState = viewModel.articleList.observeAsState()
    val batikListState = viewModelBatik.batikList.observeAsState()
    val statusState by viewModel.status.observeAsState(false)
    val loading by viewModel.loading.observeAsState(initial = false)
    val loadingBatik by viewModelBatik.loading.observeAsState(false)
    val errorState by viewModel.error.observeAsState(null)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getStory()
        viewModelBatik.getBatik()
    }

    Surface(
        color = colorScheme.surface,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .padding(horizontal = 16.dp)
        ) {
            item {
                Box(
                    modifier = modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            navController.navigate(Screen.Personalisasi.route)
                        }
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.personalisasi1),
                        contentDescription = "",
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                            .alpha(0.5f)
                    )
                    Text(
                        text = "Personalize Your Batik",
                        color = colorScheme.onPrimary,
                        fontSize = 24.sp
                    )
                }
            }
            item {
                Text(
                    text = "Belajar Tentang Batik",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onSurface,
                    modifier = modifier
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(batikListState.value ?: emptyList()) { dataBatik ->
                        BatikItem(
                            imgUrl = dataBatik?.urlBatik ?: "",
                            nameBatik = dataBatik?.name ?: "",
                            origin = dataBatik?.origin ?: "",
                            modifier = modifier.clickable {
                                dataBatik?.id?.let { navigateToDetailBatik(it) }
                            }
                        )
                    }
                }
            }
            item {
                Text(
                    text = "Article Terkini",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onSurface,
                )
            }
            if (statusState) {
                items(articleListState.value ?: emptyList()) { data ->
                    Card(
                        colors = CardDefaults.cardColors(colorScheme.onPrimary),
                        modifier = modifier
                            .clickable {
                                navigateToDetail(data.id)
                            }
                    ) {
                        ArticleItem(
                            image = data.urlBanner,
                            title = data.title,
                            createAt = data.createdAt,
                            totalLike = data.totalLike.toString(),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewArticleScreen(){
    AmbatikTheme {
        ArticelScreen(navigateToDetail = {}, navigateToDetailBatik = {})
    }
}