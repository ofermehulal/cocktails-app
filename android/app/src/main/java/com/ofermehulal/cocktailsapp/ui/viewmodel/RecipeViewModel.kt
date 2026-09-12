package com.ofermehulal.cocktailsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.ofermehulal.cocktailsapp.data.models.Recipe
import com.ofermehulal.cocktailsapp.data.models.RecipeFilter
import com.ofermehulal.cocktailsapp.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val repository = RecipeRepository(FirebaseFirestore.getInstance())

    // State
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Search results for ingredient-based search
    private val _fullMatches = MutableStateFlow<List<Recipe>>(emptyList())
    val fullMatches: StateFlow<List<Recipe>> = _fullMatches

    private val _partialMatches = MutableStateFlow<List<Recipe>>(emptyList())
    val partialMatches: StateFlow<List<Recipe>> = _partialMatches

    init {
        loadAllRecipes()
    }

    fun loadAllRecipes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val recipeList = repository.getAllRecipes()
                _recipes.value = recipeList
                _error.value = null
            } catch (e: Exception) {
                _error.value = "שגיאה בטעינת מתכונים: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchByFilter(filter: RecipeFilter) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val results = repository.searchByFilter(filter)
                _recipes.value = results
                _error.value = null
            } catch (e: Exception) {
                _error.value = "שגיאה בחיפוש: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchByIngredients(selectedIngredients: Set<String>) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val (fullMatches, partialMatches) = repository.searchByIngredients(selectedIngredients)
                _fullMatches.value = fullMatches
                _partialMatches.value = partialMatches
                _error.value = null
            } catch (e: Exception) {
                _error.value = "שגיאה בחיפוש: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRecipeById(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val recipe = repository.getRecipeById(id)
                _selectedRecipe.value = recipe
                _error.value = null
            } catch (e: Exception) {
                _error.value = "שגיאה בטעינת מתכון: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchBySpirit(spirit: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val results = repository.searchBySpirit(spirit)
                _recipes.value = results
                _error.value = null
            } catch (e: Exception) {
                _error.value = "שגיאה בחיפוש: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchByTastes(tastes: List<String>) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val results = repository.searchByTastes(tastes)
                _recipes.value = results
                _error.value = null
            } catch (e: Exception) {
                _error.value = "שגיאה בחיפוש: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
