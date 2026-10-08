package com.example.responsi.data.repository

import com.example.responsi.data.model.Meal
import com.example.responsi.data.remote.MealApiService
import com.example.responsi.data.remote.RetrofitInstance
import com.example.responsi.util.toMeal

class MealRepository(
    private val apiService: MealApiService = RetrofitInstance.api
) {
    suspend fun searchMeals(query: String): Result<List<Meal>> {
        return try {
            val response = apiService.searchMeals(query)
            val meals = response.meals?.map { it.toMeal() } ?: emptyList()
            Result.success(meals)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMealDetail(id: String): Result<Meal> {
        return try {
            val response = apiService.getMealDetail(id)
            val mealDto = response.meals?.firstOrNull()
            if (mealDto != null) {
                Result.success(mealDto.toMeal())
            } else {
                Result.failure(Exception("Resep tidak ditemukan"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
