package com.example.responsi.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi.data.model.Meal
import com.example.responsi.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MealRepository = MealRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("Semua Asal")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _favoriteMealIds = MutableStateFlow<Set<String>>(emptySet())
    val favoriteMealIds: StateFlow<Set<String>> = _favoriteMealIds.asStateFlow()

    private var allFetchedMeals: List<Meal> = emptyList()

    val categoriesList = listOf(
        "Semua Asal", "Chicken", "Beef", "Pasta", "Seafood", "Japanese", "Italian", "American", "Indian"
    )

    init {
        loadMeals("")
    }

    fun loadMeals(query: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            val effectiveQuery = query.ifBlank { "c" }
            repository.searchMeals(effectiveQuery).fold(
                onSuccess = { meals ->
                    allFetchedMeals = meals
                    applyFilter()
                },
                onFailure = { error ->
                    _uiState.value = HomeUiState.Error(
                        error.localizedMessage ?: "Terjadi kesalahan saat memuat resep."
                    )
                }
            )
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
        loadMeals(newQuery)
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
        applyFilter()
    }

    fun toggleFavorite(meal: Meal) {
        val current = _favoriteMealIds.value.toMutableSet()
        if (current.contains(meal.id)) {
            current.remove(meal.id)
        } else {
            current.add(meal.id)
        }
        _favoriteMealIds.value = current
    }

    private fun applyFilter() {
        val cat = _selectedCategory.value
        val filtered = if (cat.equals("Semua Asal", ignoreCase = true) || cat.equals("Semua", ignoreCase = true)) {
            allFetchedMeals
        } else {
            allFetchedMeals.filter {
                it.category.contains(cat, ignoreCase = true) || it.area.contains(cat, ignoreCase = true)
            }
        }

        _uiState.value = HomeUiState.Success(
            meals = filtered,
            categories = categoriesList,
            selectedCategory = _selectedCategory.value,
            searchQuery = _searchQuery.value
        )
    }
}
