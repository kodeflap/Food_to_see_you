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

    @ColumnInfo(name = "strMeal")
    @SerializedName("strMeal")
    @Expose
    val strMeal: String,

    @ColumnInfo(name = "strMealThumb")
    @SerializedName("strMealThumb")
    @Expose
    val strMealThumb: String
)