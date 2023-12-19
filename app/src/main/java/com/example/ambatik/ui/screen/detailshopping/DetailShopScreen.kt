package com.example.ambatik.ui.screen.detailshopping

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ambatik.R
import com.example.ambatik.data.factory.CartModelFactory
import com.example.ambatik.data.factory.ShopModelFactory
import com.example.ambatik.data.pref.UserModel
import com.example.ambatik.data.pref.UserPreference
import com.example.ambatik.data.pref.dataStore
import com.example.ambatik.ui.components.alert.AlertLogin
import com.example.ambatik.ui.components.shop.OrderButton
import com.example.ambatik.ui.theme.AmbatikTheme
import com.example.ambatik.utlis.formatCurrency

@Composable
fun DetailShopScreen(
    navController: NavHostController = rememberNavController(),
    shopId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailShopViewModel = viewModel(
        factory = ShopModelFactory.getInstance(LocalContext.current)
    ),
    viewModelCart: AddToCartViewModel = viewModel(
        factory = CartModelFactory.getInstance(LocalContext.current)
    ),
    navigateToWelcome: () -> Unit,
    userPreference: UserPreference = UserPreference.getInstance(LocalContext.current.dataStore)
) {
    val shopListState = viewModel.detailShop.observeAsState()
    val userModel by userPreference.getSession().collectAsState(initial = UserModel("", "", false, 0))
    val command = "add"
    val context = LocalContext.current

    var alertLogin = remember { mutableStateOf(true) }

    if (!alertLogin.value) {
        AlertLogin(
            isLogin = alertLogin.value,
            navigateToWelcome = {
                navigateToWelcome()
            }
        )
    }

    LaunchedEffect(Unit){
        viewModel.getDetailShop(shopId)
    }

    Surface(
        color = colorScheme.surface,
        modifier = modifier
            .fillMaxWidth()
    ) {
        shopListState.value?.let {detailShop ->
            DetailShopContent(
                image = detailShop.urlProduct ?: "",
                nameProduct = detailShop.name ?: "",
                price = detailShop.price ?: 0,
                store = detailShop.storeName ?: "",
                rating = 5.0,
                description = detailShop.description ?: "",
                count = 1,
                onAddToCart = {
                    if (userModel.isLogin){
                        viewModelCart.addToCart(userModel.id, shopId, command)
                        Toast.makeText(context, "Berhasil menambahkan product ke cart", Toast.LENGTH_SHORT).show()
                    }else{
                        alertLogin.value = userModel.isLogin
                    }
                }
            )
        }
    }
}

@Composable
fun DetailShopContent(
    image: String,
    nameProduct: String,
    price: Int,
    store: String,
    rating: Double,
    description: String,
    count: Int,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier
) {

    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box(
            ){
                AsyncImage(
                    model = image,
                    contentDescription = "Image Detail Article",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
            }
            Box(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ){
                Column {
                    Box{
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = formatCurrency(price.toDouble()),
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.ExtraBold
                                ),
                                color = colorScheme.onSurface,
                            )
                        }
                    }
                    Box(
                        modifier = modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    ){
                        Column {
                            Text(
                                text = nameProduct,
                                style = MaterialTheme.typography.titleLarge,
                                color = colorScheme.onSurface,
                            )
                            Text(
                                text = store,
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorScheme.onSurface,
                            )
                            Box(
                                modifier = modifier
                                    .padding(top = 8.dp, bottom = 8.dp)
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = Color(0xFFFF9529),
                                        modifier = modifier
                                    )
                                    Text(
                                        text = rating.toString(),
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.ExtraBold
                                        ),
                                        color = colorScheme.onSurface,
                                        modifier = modifier
                                            .padding(start = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        color = colorScheme.onSurface,
                    )
                }
            }

        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.LightGray))
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ){
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                OrderButton(
                    text = stringResource(R.string.add_to_cart),
                    enabled = orderCount > 0,
                    onClick = {
                        onAddToCart()
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(end = 12.dp)
                )
            }
        }
    }

}

@Preview
@Composable
fun DetailShopContentPreview() {
    AmbatikTheme {
        DetailShopContent(
            "",
            "Batik Keris Cirebon",
            50000,
            "Batik Jaya",
            4.3,
            stringResource(R.string.lorem_ipsum),
            1,
            onAddToCart = {}
        )
    }
}