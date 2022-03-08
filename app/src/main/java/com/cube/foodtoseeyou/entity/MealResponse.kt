package com.cube.foodtoseeyou.entity

import android.support.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class MealResponse(
    @SerializedName("meals")
    @Expose
    val meals: List<MealEntity>
)