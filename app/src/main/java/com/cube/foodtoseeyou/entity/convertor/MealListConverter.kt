package com.cube.foodtoseeyou.entity.convertor

import androidx.room.TypeConverter
import com.cube.foodtoseeyou.entity.Category
import com.cube.foodtoseeyou.entity.MealItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealListConverter {
    @TypeConverter
    fun fromCategoryList(category: List<MealItem>): String? {
        return run {
            val gson = Gson()
            val type = object : TypeToken<MealItem>() {
            }.type
            gson.toJson(category, type)
        }
    }

    @TypeConverter
    fun toCategoryList(categoryString : String): List<MealItem>? {
        return run {
            val gson = Gson()
            val type = object :TypeToken<MealItem>() {
            }.type
            gson.fromJson(categoryString,type)
        }
    }
}