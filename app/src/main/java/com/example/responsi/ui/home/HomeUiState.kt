package com.example.responsi.ui.home

import com.example.responsi.data.model.Meal

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val meals: List<Meal>,
        val categories: List<String>,
        val selectedCategory: String,
        val searchQuery: String
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
