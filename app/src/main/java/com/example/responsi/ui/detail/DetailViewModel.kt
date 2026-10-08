package com.example.responsi.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MealRepository = MealRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    private val _checkedIngredients = MutableStateFlow<Set<Int>>(emptySet())
    val checkedIngredients: StateFlow<Set<Int>> = _checkedIngredients.asStateFlow()

    fun loadMealDetail(mealId: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            repository.getMealDetail(mealId).fold(
                onSuccess = { meal ->
                    _uiState.value = DetailUiState.Success(meal)
                },
                onFailure = { error ->
                    _uiState.value = DetailUiState.Error(
                        error.localizedMessage ?: "Gagal memuat detail resep."
                    )
                }
            )
        }
    }

    fun toggleIngredientCheck(index: Int) {
        val current = _checkedIngredients.value.toMutableSet()
        if (current.contains(index)) {
            current.remove(index)
        } else {
            current.add(index)
        }
        _checkedIngredients.value = current
    }

    fun resetIngredients() {
        _checkedIngredients.value = emptySet()
    }
}
