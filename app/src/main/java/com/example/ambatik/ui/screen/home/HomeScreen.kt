package com.example.ambatik.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.R
import com.example.ambatik.data.factory.UserModelFactory
import com.example.ambatik.ui.navigation.Navigation
import com.example.ambatik.ui.navigation.NavigationBottomBar
import com.example.ambatik.ui.navigation.NavigationItem
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = UserModelFactory.getInstance(LocalContext.current)
    )
){
    val lifecycleOwner = LocalLifecycleOwner.current

    viewModel.getSession().observe(lifecycleOwner) { session ->
        if (!session.isLogin){
            navController.navigate(Screen.Welcome.route)
        }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        floatingActionButton = {
            if (currentRoute != Screen.Scan.route ){
                FAB(navController)
            }else{
                FAB(
                    navController,
                    isVisible = false
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            if (currentRoute != Screen.Scan.route){
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavigationBottomBar(navController, innerPadding )
    }
}

@Composable
private fun FAB(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (isVisible){
        FloatingActionButton(
            onClick = {
                if (currentRoute != Screen.Scan.route){
                    navController.navigate(Screen.Scan.route)
                }
            },
            shape = CircleShape,
            modifier = Modifier
                .size(65.dp)
                .offset(y = 60.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.scanner),
                contentDescription = "Scan Batik",
                modifier = modifier
                    .padding(15.dp)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    BottomAppBar(
        modifier = modifier
            .clip(
                RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.articel_screen),
                icon = Icons.Default.Home,
                screen = Screen.Articel
            ),
            NavigationItem(
                title = stringResource(R.string.quiz_screen),
                icon = Icons.Default.MenuBook,
                screen = Screen.Quiz
            ),
            NavigationItem(
                title = "Scan",
                icon = null,
                screen = null
            ),
            NavigationItem(
                title = stringResource(R.string.shop_screen),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Shopping
            ),
            NavigationItem(
                title = stringResource(R.string.profile_screen),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            )
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    item.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = item.title
                        )
                    }
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen?.route,
                onClick = {
                    item.screen?.let {
                        navController.navigate(it.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    AmbatikTheme {
        HomeScreen()
    }
}