package com.cube.foodtoseeyou.interfaces

import com.cube.foodtoseeyou.entity.Category
import com.cube.foodtoseeyou.entity.Meal
import com.cube.foodtoseeyou.entity.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetData {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>
}