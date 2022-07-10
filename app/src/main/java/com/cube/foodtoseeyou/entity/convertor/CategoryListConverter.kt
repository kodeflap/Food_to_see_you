package com.cube.foodtoseeyou.entity.convertor

import androidx.room.TypeConverter
import com.cube.foodtoseeyou.entity.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryListConverter {
    @TypeConverter
    fun fromCategoryList(category: List<CategoryItems>): String? {
        return run {
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>() {
            }.type
            gson.toJson(category, type)
        }
    }

    @TypeConverter
    fun toCategoryList(categoryString: String): List<CategoryItems>? {
        return run {
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>() {
            }.type
            gson.fromJson(categoryString, type)
        }
    }
}