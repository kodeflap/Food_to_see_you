package com.cube.foodtoseeyou.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("meals")
    @Expose
    val mealsEntity: List<MealEntity>
)