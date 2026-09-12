package com.ofermehulal.cocktailsapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.ofermehulal.cocktailsapp.data.models.Recipe
import com.ofermehulal.cocktailsapp.data.models.RecipeFilter
import kotlinx.coroutines.tasks.await

class RecipeRepository(private val firestore: FirebaseFirestore) {

    suspend fun getAllRecipes(): List<Recipe> {
        return try {
            firestore.collection("recipes")
                .get()
                .await()
                .toObjects(Recipe::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getRecipeById(id: String): Recipe? {
        return try {
            firestore.collection("recipes")
                .document(id)
                .get()
                .await()
                .toObject(Recipe::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun searchBySpirit(spirit: String): List<Recipe> {
        return try {
            firestore.collection("recipes")
                .whereEqualTo("baseSpirit", spirit)
                .get()
                .await()
                .toObjects(Recipe::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchByTastes(tastes: List<String>): List<Recipe> {
        return try {
            val recipes = firestore.collection("recipes")
                .get()
                .await()
                .toObjects(Recipe::class.java)

            recipes.filter { recipe ->
                tastes.any { taste -> recipe.tastes.contains(taste) }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchByFilter(filter: RecipeFilter): List<Recipe> {
        return try {
            var query = firestore.collection("recipes")

            filter.spirit?.let {
                query = query.whereEqualTo("baseSpirit", it) as com.google.firebase.firestore.Query
            }

            val recipes = query.get().await().toObjects(Recipe::class.java)

            return recipes.filter { recipe ->
                val matchesDifficulty = filter.difficulty?.let {
                    recipe.difficulty == it
                } ?: true

                val matchesCost = filter.cost?.let {
                    recipe.cost == it
                } ?: true

                val matchesTastes = if (filter.tastes.isNotEmpty()) {
                    filter.tastes.any { taste -> recipe.tastes.contains(taste) }
                } else {
                    true
                }

                matchesDifficulty && matchesCost && matchesTastes
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchByIngredients(selectedIngredients: Set<String>): Pair<List<Recipe>, List<Recipe>> {
        return try {
            val allRecipes = firestore.collection("recipes")
                .get()
                .await()
                .toObjects(Recipe::class.java)

            val fullMatches = mutableListOf<Recipe>()
            val partialMatches = mutableListOf<Recipe>()

            allRecipes.forEach { recipe ->
                val recipeIngredientIds = recipe.ingredients.map { it.id }.toSet()
                val matchCount = selectedIngredients.count { it in recipeIngredientIds }
                val matchPercentage = if (recipeIngredientIds.isNotEmpty()) {
                    (matchCount.toFloat() / recipeIngredientIds.size) * 100
                } else {
                    0f
                }

                when {
                    matchCount == recipeIngredientIds.size -> fullMatches.add(recipe)
                    matchPercentage >= 80 -> partialMatches.add(recipe)
                }
            }

            Pair(fullMatches, partialMatches)
        } catch (e: Exception) {
            Pair(emptyList(), emptyList())
        }
    }
}
