package com.example.ambatik.ui.screen.articlelike

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.data.factory.ArticleModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.article.ArticleItem
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun ArticleLikeScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: ArticleLikeViewModel = viewModel(
        factory = ArticleModelFactory.getInstance(LocalContext.current)
    ),
    navigateToDetail: (Int) -> Unit,
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore)
){
    val likeListArticleState = viewModel.likeArticleList.observeAsState()
    val statusState by viewModel.status.observeAsState(false)
    val errorState by viewModel.error.observeAsState(null)
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))

    LaunchedEffect(userModel.id){
        viewModel.getLikeArticleList(userModel.id)
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
        if (statusState){
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ){
                items(likeListArticleState.value ?: emptyList()){data ->
                    Card(
                        colors = CardDefaults.cardColors(Color.Gray),
                        modifier = modifier
                            .clickable {
                                data?.id?.let { navigateToDetail(it) }
                            }
                    ){
                        ArticleItem(
                            image = data?.urlBanner ?: "",
                            title = data?.title ?: "",
                            createAt = "2023-23-23",
                            totalLike = data?.totalLike.toString() ?: "",
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewArticleLikeScreen(){
    AmbatikTheme {
        ArticleLikeScreen(navigateToDetail = {})
    }
}