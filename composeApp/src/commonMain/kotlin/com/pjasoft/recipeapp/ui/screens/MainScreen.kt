package com.pjasoft.recipeapp.ui.screens

import HomeScreenRoute
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pjasoft.recipeapp.ui.screens.HomeScreen.HomeScreen

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute
    ){
        composable<HomeScreenRoute> {
            HomeScreen()
        }
    }
}