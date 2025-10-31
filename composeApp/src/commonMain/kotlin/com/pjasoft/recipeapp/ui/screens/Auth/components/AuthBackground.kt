package com.pjasoft.recipeapp.ui.screens.Auth.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun AuthBackground(
    content : @Composable () -> Unit
){
    Box(){
        content
    }
}

@Composable
fun CustomCard(content : @Composable () -> Unit, onClick : () -> Unit){
    Column {
        content
    }
}
