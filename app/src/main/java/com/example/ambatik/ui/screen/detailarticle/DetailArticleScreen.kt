package com.example.ambatik.ui.screen.detailarticle

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ambatik.data.factory.ArticleModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.alert.AlertLogin
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun DetailArticleScreen(
    articleId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailArticleViewModel = viewModel(
        factory = ArticleModelFactory.getInstance(LocalContext.current)
    ),
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore),
    navigateToWelcome: () -> Unit,
) {
    val articleListState = viewModel.detailArticle.observeAsState()
    val articleListNoLoginState = viewModel.detailArticleNoLogin.observeAsState()
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))

    if (userModel.isLogin){
        LaunchedEffect(userModel.id){
            viewModel.getDetailStory(articleId, userModel.id)
        }
    }else{
        LaunchedEffect(Unit){
            viewModel.getDetailStoryNoLogin(articleId)
        }
    }

    Surface(
        color = colorScheme.surface,
        modifier = modifier
            .fillMaxWidth()
    ) {
        if (userModel.isLogin){
            articleListState.value?.let { detailArticle ->
                var holdLike by remember { mutableStateOf("") }
                if (detailArticle.likes.isEmpty()){
                    holdLike = "0"
                }else{
                    holdLike = detailArticle.likes.firstOrNull()!!.statusLike
                }
                Log.d("DetailArticleContent", "${detailArticle.likes.firstOrNull()?.statusLike}")
                DetailArticleContent(
                    image = detailArticle.urlBanner ?: "",
                    title = detailArticle.title ?: "",
                    author = detailArticle.author ?: "",
                    createAt = detailArticle.createdAt ?: "",
                    totalLike = detailArticle.totalLike,
                    description = detailArticle.content ?: "",
                    isLiked = holdLike,
                    isLogin = userModel.isLogin,
                    onLikeClick = { viewModel.likeArticle(userModel.id, articleId) },
                    navigateToWelcome = {
                        navigateToWelcome()
                    },
                    modifier = Modifier
                )
            }
        }else{
            articleListNoLoginState.value?.let { detailArticle ->
                var holdLike by remember { mutableStateOf("") }
                if (detailArticle.likes.isNotEmpty()){
                    holdLike = "0"
                }
                Log.d("DetailArticleContent", "${detailArticle.likes.firstOrNull()?.statusLike}")
                DetailArticleContent(
                    image = detailArticle.urlBanner ?: "",
                    title = detailArticle.title ?: "",
                    author = detailArticle.author ?: "",
                    createAt = detailArticle.createdAt ?: "",
                    totalLike = detailArticle.totalLike,
                    description = detailArticle.content ?: "",
                    isLiked = holdLike,
                    isLogin = userModel.isLogin,
                    onLikeClick = { viewModel.likeArticle(userModel.id, articleId) },
                    navigateToWelcome = {
                        navigateToWelcome()
                    },
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun DetailArticleContent(
    image: String,
    title: String,
    author: String,
    createAt: String,
    totalLike: Int,
    description: String,
    isLiked: String,
    isLogin: Boolean,
    onLikeClick: () -> Unit,
    navigateToWelcome: ()-> Unit,
    modifier: Modifier = Modifier
){
    var alertLogin = remember { mutableStateOf(true) }

    if (!alertLogin.value) {
        AlertLogin(
            isLogin = alertLogin.value,
            navigateToWelcome = {
                navigateToWelcome()
            }
        )
    }
    var liked by remember { mutableStateOf(isLiked == "1") }
    var totalLikeCount by remember { mutableStateOf(totalLike) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                model = image,
                contentDescription = "Image Detail Article",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
            )
            Box(
                modifier = modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ){
                Box(
                    modifier = modifier
                        .padding(15.dp, 15.dp)
                        .size(75.dp, 50.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .alpha(0.5f)
                        .background(Color.Black)
                        .clickable {
                            if (isLogin) {
                                liked = !liked
                                totalLikeCount += if (liked) 1 else -1
                                onLikeClick()
                            } else {
                                alertLogin.value = isLogin
                            }
                        }
                )
                Box(
                    modifier = modifier
                        .padding(25.dp, 15.dp)
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ){
                    Row{
                        Icon(
                            imageVector = if (liked) Icons.Outlined.Favorite else Icons.Filled.FavoriteBorder,
                            tint = Color.Red,
                            contentDescription = "Icon Like Article",
                            modifier = modifier
                        )
                        Text(
                            text = totalLikeCount.toString(),
                            color = colorScheme.onPrimary,
                            modifier = modifier
                                .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        )

                    }
                }

            }
        }
        Box(
            modifier = modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Column {
                Box {
                    Row {
                        Text(
                            text = author,
                            color = colorScheme.onSurface
                        )
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                text = createAt,
                                color = colorScheme.onSurface,
                                textAlign = TextAlign.Right,
                            )
                        }
                    }
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    ),
                    color = colorScheme.onSurface,
                    modifier = modifier
                        .padding(0.dp, 12.dp, 0.dp, 20.dp)
                )
                Text(
                    text = description,
                    color = colorScheme.onSurface,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailArticle(){
    AmbatikTheme {
        DetailArticleContent(
            image = "",
            title = "Yuk, Rayakan Hari Batik Nasional dalam Rangkaian Acara Keren GANTARI!",
            createAt = "2023-11-23",
            totalLike = 1,
            author = "Wiguna Wijaya",
            description = "Dalam penyelenggaraan kali ini, LAKON Indonesia akan mempresentasikan koleksi yang lebih matang dan lebih dalam, berupa 125 koleksi pakaian siap pakai yang akan diperagakan oleh 100 orang model.",
            isLiked = "1",
            onLikeClick = {},
            isLogin = true,
            navigateToWelcome = {}
        )
    }
}