package com.pjasoft.recipeapp

import LoginScreenRoute
import MainScreenGraph
import MainScreenRoute
import RecipeTheme
import RegisterScreenRoute
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.pjasoft.recipeapp.ui.screens.Auth.LoginScreen
import com.pjasoft.recipeapp.ui.screens.Auth.RegisterScreen
import com.pjasoft.recipeapp.ui.screens.MainScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

// Patron Observable
@Composable
@Preview
fun App() {
    RecipeTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = MainScreenGraph
        ){
            composable<RegisterScreenRoute> {
                RegisterScreen(
                    navController = navController
                )
            }


            composable<LoginScreenRoute> {
                LoginScreen(
                    navController = navController
                )
            }

            navigation<MainScreenGraph>(
                startDestination = MainScreenRoute
            ){
               composable<MainScreenRoute> {
                   MainScreen()
               }
            }
        }
    }
}