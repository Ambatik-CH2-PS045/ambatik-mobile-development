package com.example.ambatik.ui.screen.shopping

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.R
import com.example.ambatik.data.factory.ShopModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.alert.AlertLogin
import com.example.ambatik.ui.components.shop.ProductBatikItem
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.theme.AmbatikTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: ShoppingViewModel = viewModel(
        factory = ShopModelFactory.getInstance(LocalContext.current)
    ),
    navigateToDetailShop: (Int) -> Unit,
    navigateToWelcome: () -> Unit,
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore),
) {
    val shopListState = viewModel.shopList.observeAsState()
    val statusState by viewModel.status.observeAsState(false)
    val errorState by viewModel.error.observeAsState(null)
    val query by viewModel.query

    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))
    var alertLogin = remember { mutableStateOf(true) }

    LaunchedEffect(Unit){
        viewModel.getShop()
    }

    if (!alertLogin.value) {
        AlertLogin(
            isLogin = alertLogin.value,
            navigateToWelcome = {
                navigateToWelcome()
            }
        )
    }

    Surface(
        color = colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    onSearch = {},
                    active = false,
                    onActiveChange = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = SearchBarDefaults.colors(containerColor = Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = colorScheme.onSurface
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Cari batik",
                            fontSize = 14.sp,
                        )
                    },
                    modifier = modifier
                        .padding(16.dp, 16.dp, 0.dp, 16.dp)
                        .fillMaxWidth(0.85f)
                        .height(55.dp)
                ) {
                }
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Shopping Cart",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(40.dp)
                        .clickable {
                            if (userModel.isLogin){
                                navController.navigate(Screen.Cart.route)
                            }else{
                                alertLogin.value = userModel.isLogin
                            }
                        }
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
            ){
                items(shopListState.value ?: emptyList()){ data ->
                    ProductBatikItem(
                        image = data.urlProduct,
                        nameProduct = data.name,
                        price = data.price,
                        store = data.storeName,
                        rating = data.rating,
                        productSold = data.productSold,
                        modifier = modifier
                            .clickable {
                                navigateToDetailShop(data.id)
                            }
                            .animateItemPlacement(tween(durationMillis = 100))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewShoppingScreen(){
    AmbatikTheme {
        ShoppingScreen(
            navigateToDetailShop = {},
            navigateToWelcome = {}
        )
    }
}