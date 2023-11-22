package com.example.ambatik.ui.screen.home

import android.util.Log
import android.view.RoundedCorner
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ambatik.R
import com.example.ambatik.ui.navigation.NavigationItem
import com.example.ambatik.ui.navigation.Screen
import com.example.ambatik.ui.screen.articel.ArticelScreen
import com.example.ambatik.ui.screen.profile.ProfileScreen
import com.example.ambatik.ui.screen.quiz.QuizScreen
import com.example.ambatik.ui.screen.scan.ScanScreen
import com.example.ambatik.ui.screen.shopping.ShoppingScreen
import com.example.ambatik.ui.theme.AmbatikTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        floatingActionButton = {
            if (currentRoute != Screen.Scan.route) {
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Articel.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Articel.route){
                ArticelScreen()
            }
            composable(Screen.Quiz.route){
                QuizScreen()
            }
            composable(Screen.Scan.route){
                ScanScreen()
            }
            composable(Screen.Shopping.route){
                ShoppingScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
        }
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
                .size(70.dp)
                .offset(y = 65.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Camera,
                contentDescription = "",
                modifier = modifier
                    .padding(12.dp)
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
                title = "",
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