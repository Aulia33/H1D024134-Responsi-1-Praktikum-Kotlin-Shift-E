package com.example.responsi.data.model

data class Meal(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnailUrl: String,
    val youtubeUrl: String?,
    val sourceUrl: String?,
    val tags: List<String>,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val rating: Double,
    val durationMinutes: Int,
    val difficulty: String,
    val servings: Int,
    val calories: Int
)
