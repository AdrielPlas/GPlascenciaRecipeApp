package com.pjasoft.recipeapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjasoft.recipeapp.data.KtorfitClient
import com.pjasoft.recipeapp.domain.dtos.Prompt
import com.pjasoft.recipeapp.domain.dtos.RecipeDTO
import com.pjasoft.recipeapp.domain.models.Recipe
import kotlinx.coroutines.launch

class RecipeViewModel(
    val userId: Int
) : ViewModel() {
    val recipeService = KtorfitClient.createRecipeService()
    var recipes by mutableStateOf<List<Recipe>>(listOf())
    var recentRecipes by mutableStateOf<List<Recipe>>(listOf())
    var generatedRecipe by mutableStateOf<RecipeDTO?>(null)
    var showSheet by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    init {
        getRecipes()
    }

    fun showModalFromList(recipe: RecipeDTO){
        generatedRecipe = recipe
        showSheet = true
    }

    fun hideModal(){
        showSheet = false
    }

    fun generateRecipe(prompt: Prompt){
        viewModelScope.launch {
            try {
                isLoading = true
                val result = recipeService.generateRecipe(prompt)
                showSheet = true
                generatedRecipe = result
                println(result.toString())
            }
            catch (e: Exception) {
                showSheet = false
                println(e.toString())
            }
            finally {
                isLoading = false
            }
        }
    }

    fun getRecipes(){
        viewModelScope.launch {
            try {
                val result = recipeService.getRecipesByUserId(userId)
                recipes = result.reversed()
                recentRecipes = result.takeLast(5).reversed()
                println(result.toString())
            }
            catch (e: Exception) {
                println(e.toString())
            }
        }
    }

    fun saveRecipe(recipeDTO: RecipeDTO){
        viewModelScope.launch {
            try {
                isLoading = true
                val recipe = Recipe(
                    id = 0,
                    title = recipeDTO.title,
                    category = recipeDTO.category,
                    imageUrl = recipeDTO.imageUrl,
                    ingredients = recipeDTO.ingredients,
                    instructions = recipeDTO.instructions,
                    minutes = recipeDTO.minutes,
                    stars = recipeDTO.stars,
                    userId = userId
                )
                recipeService.saveGeneratedRecipe(recipe)
                getRecipes() // Recargar recetas
                hideModal()
            }
            catch (e: Exception) {
                println(e.toString())
            }
            finally {
                isLoading = false
            }
        }
    }
}