package com.cube.foodtoseeyou.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cube.foodtoseeyou.entity.convertor.MealListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Meal")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "meals")
    @SerializedName("meals")
    @TypeConverters(MealListConverter::class)
    @Expose
    var mealsItem: List<MealItem>? = null
)