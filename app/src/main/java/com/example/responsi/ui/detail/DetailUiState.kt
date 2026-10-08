package com.example.responsi.ui.detail

import com.example.responsi.data.model.Meal

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val meal: Meal) : DetailUiState
    data class Error(val message: String) : DetailUiState
}
