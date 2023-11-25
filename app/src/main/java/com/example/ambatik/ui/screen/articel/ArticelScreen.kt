package com.example.ambatik.ui.screen.articel

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.data.factory.UserModelFactory
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.screen.welcome.WelcomeViewModel
import com.example.ambatik.ui.theme.AmbatikTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ambatik.data.factory.ArticleModelFactory
import com.example.ambatik.di.Injection
import com.example.ambatik.ui.components.ArticleItem

@Composable
fun ArticelScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: ArticleViewModel = viewModel(
        factory = ArticleModelFactory.getInstance(LocalContext.current)
    )
){
    val articleListState = viewModel.articleList.observeAsState()
    val statusState by viewModel.status.observeAsState(false)
    val errorState by viewModel.error.observeAsState(null)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getStory()
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
                items(articleListState.value ?: emptyList()){ data ->
                    Card{
                        ArticleItem(
                            image = data.urlBanner,
                            title = data.title,
                            createAt = "20-20-2023",
                            description = data.content,
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
        ArticelScreen()
    }
}