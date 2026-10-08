package com.example.responsi.data.remote

import com.example.responsi.data.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") id: String): MealResponse
}
