package com.cube.foodtoseeyou.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MealItems")
data class MealItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "idMeal")
    @SerializedName("idMeal")
    @Expose
    val idMeal: String,

    @ColumnInfo(name = "categoryName")
    val categoryName: String,

    @ColumnInfo(name = "strmeal")
    @SerializedName("strMeal")
    @Expose
    val strMeal: String,

    @ColumnInfo(name = "strmealthumb")
    @SerializedName("strMealThumb")
    @Expose
    val strMealThumb: String
)