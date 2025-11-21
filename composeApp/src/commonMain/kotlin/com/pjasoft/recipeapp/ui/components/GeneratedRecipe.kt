package com.pjasoft.recipeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.pjasoft.recipeapp.domain.dtos.RecipeDTO
import com.pjasoft.recipeapp.domain.models.Recipe
import com.pjasoft.recipeapp.ui.screens.HomeScreen.components.Tag

@Composable
fun GeneratedRecipe(
    recipe: RecipeDTO?,
    onSaveClick: () -> Unit = {}
){
    val colors = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = recipe?.imageUrl,
            contentDescription = recipe?.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(colors.primary.copy(0.1f))
        )
        Text(
            text = recipe?.title ?: "",
            modifier = Modifier.padding(vertical = 9.dp),
            style = MaterialTheme
                .typography
                .headlineMedium
                .copy(fontWeight = FontWeight.Bold)

        )
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(colors.primary.copy(alpha = 0.15f))
                .padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Star, contentDescription = null, tint = colors.primary)
            Spacer(Modifier.width(4.dp))
            Text("${recipe?.stars}", color = colors.primary, fontWeight = FontWeight.Bold)

            Spacer(Modifier.width(12.dp))

            Icon(Icons.Default.Schedule, contentDescription = null, tint = colors.primary)
            Spacer(Modifier.width(4.dp))
            Text("${recipe?.minutes} min", color = colors.primary)

            Spacer(Modifier.width(12.dp))

            Text(
                recipe?.category ?: "",
                color = colors.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            text = "Ingredientes",
            modifier = Modifier.padding(vertical = 9.dp),
            style = MaterialTheme
                .typography
                .headlineSmall
                .copy(fontWeight = FontWeight.SemiBold)
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            recipe?.ingredients?.forEach { ingredient ->
                Tag(
                    text = ingredient
                )
            }
        }
        Text(
            text = "Preparación",
            modifier = Modifier.padding(vertical = 9.dp),
            style = MaterialTheme
                .typography
                .headlineSmall
                .copy(fontWeight = FontWeight.SemiBold)
        )
        recipe?.instructions?.forEachIndexed { index, instruction ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "${ index + 1 }. ",
                    color = colors.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = instruction,
                    color = colors.onSurface
                )
            }
        }
        
        Spacer(Modifier.height(16.dp))
        
        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary
            )
        ) {
            Icon(Icons.Default.Save, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Guardar Receta")
        }
    }
}