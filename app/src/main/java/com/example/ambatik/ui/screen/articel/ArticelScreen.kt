package com.example.ambatik.ui.screen.articel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.data.factory.ArticleModelFactory
import com.example.ambatik.data.factory.BatikModelFactory
import com.example.ambatik.ui.components.article.ArticleItem
import com.example.ambatik.ui.components.batik.BatikItem
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
                Text(
                    text = "Belajar Tentang Batik",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = colorScheme.onSurface,
                    modifier = modifier
                        .padding(top = 12.dp)
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
                    fontWeight = FontWeight.Black,
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