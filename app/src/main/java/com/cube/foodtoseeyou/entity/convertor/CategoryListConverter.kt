package com.cube.foodtoseeyou.entity.convertor

import androidx.room.TypeConverter
import com.cube.foodtoseeyou.entity.Category
import com.cube.foodtoseeyou.entity.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryListConverter {
    @TypeConverter
    fun fromCategoryList(category: List<CategoryItems>): String? {
        if (category == null)
            return null
        else {
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>() {

            }.type
            return gson.toJson(category, type)
        }
    }

    @TypeConverter
    fun toCategortList(categoryString : String): List<CategoryItems>? {
        if (categoryString == null)
            return null
        else {
            val gson = Gson()
            val type = object :TypeToken<CategoryItems>() {

            }.type
            return gson.fromJson(categoryString,type)
        }
    }
}