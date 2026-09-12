package com.ofermehulal.cocktailsapp.data.models

data class Recipe(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val baseSpirit: String = "",
    val tastes: List<String> = emptyList(),
    val difficulty: String = "",
    val cost: String = "",
    val imageUrl: String = "",
    val ingredients: List<Ingredient> = emptyList(),
    val instructions: List<String> = emptyList(),
    val garnish: String = "",
    val tips: String = ""
)

data class Ingredient(
    val id: String = "",
    val name: String = "",
    val amount: String = ""
)

// For Firebase queries
data class RecipeFilter(
    val spirit: String? = null,
    val tastes: List<String> = emptyList(),
    val difficulty: String? = null,
    val cost: String? = null
)

data class IngredientFilter(
    val selectedIngredients: Set<String> = emptySet()
)
