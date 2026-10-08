package com.example.responsi.util

import com.example.responsi.data.model.Ingredient
import com.example.responsi.data.model.Meal
import com.example.responsi.data.model.MealDto
import java.util.Locale

fun String.capitalizeWords(): String {
    return this.split(" ")
        .joinToString(" ") { word ->
            word.lowercase(Locale.ROOT)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }
}

fun List<Ingredient>.toIngredientSummary(): String {
    return if (isEmpty()) "Tidak ada bahan" else "${size} Bahan Utama"
}

fun MealDto.toMeal(): Meal {
    val rawIngredients = listOf(
        strIngredient1 to strMeasure1,
        strIngredient2 to strMeasure2,
        strIngredient3 to strMeasure3,
        strIngredient4 to strMeasure4,
        strIngredient5 to strMeasure5,
        strIngredient6 to strMeasure6,
        strIngredient7 to strMeasure7,
        strIngredient8 to strMeasure8,
        strIngredient9 to strMeasure9,
        strIngredient10 to strMeasure10,
        strIngredient11 to strMeasure11,
        strIngredient12 to strMeasure12,
        strIngredient13 to strMeasure13,
        strIngredient14 to strMeasure14,
        strIngredient15 to strMeasure15,
        strIngredient16 to strMeasure16,
        strIngredient17 to strMeasure17,
        strIngredient18 to strMeasure18,
        strIngredient19 to strMeasure19,
        strIngredient20 to strMeasure20
    )

    val ingredientsList = rawIngredients
        .filter { !it.first.isNullOrBlank() }
        .map { (ing, meas) ->
            Ingredient(
                name = ing?.trim().orEmpty(),
                measure = meas?.trim().orEmpty().ifBlank { "Secukupnya" }
            )
        }

    val rawSteps = strInstructions.orEmpty()
        .split(Regex("\r\n|\n|\r"))
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .flatMap { line ->
            if (line.matches(Regex("""^\d+\..*"""))) {
                listOf(line.replace(Regex("""^\d+\.\s*"""), ""))
            } else {
                listOf(line)
            }
        }
        .filter { it.length > 5 }

    val parsedSteps = if (rawSteps.isEmpty()) {
        listOf("Siapkan bahan-bahan yang telah disediakan.", "Campur dan masak hingga matang.", "Sajikan selagi hangat.")
    } else {
        rawSteps
    }

    val tagList = strTags.orEmpty()
        .split(",")
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .map { if (it.startsWith("#")) it else "#$it" }
        .ifEmpty { listOf("#LaukUtama", "#Sehat", "#ResepRumahan") }

    val seed = (idMeal?.toIntOrNull() ?: 100)
    val calculatedRating = 4.5 + ((seed % 6) * 0.1)
    val roundedRating = String.format(Locale.US, "%.1f", calculatedRating).toDoubleOrNull() ?: 4.8
    val duration = 20 + ((seed % 5) * 5)
    val difficultyLevel = when (seed % 3) {
        0 -> "Mudah"
        1 -> "Menengah"
        else -> "Mahir"
    }
    val servingCount = 2 + ((seed % 3) * 2)
    val cal = 250 + ((seed % 7) * 40)

    return Meal(
        id = idMeal.orEmpty(),
        name = strMeal.orEmpty(),
        category = strCategory.orEmpty().ifBlank { "Umum" },
        area = strArea.orEmpty().ifBlank { "Internasional" },
        instructions = strInstructions.orEmpty(),
        thumbnailUrl = strMealThumb.orEmpty(),
        youtubeUrl = strYoutube?.ifBlank { null },
        sourceUrl = strSource?.ifBlank { null },
        tags = tagList,
        ingredients = ingredientsList,
        steps = parsedSteps,
        rating = roundedRating,
        durationMinutes = duration,
        difficulty = difficultyLevel,
        servings = servingCount,
        calories = cal
    )
}
