package com.cube.foodtoseeyou.entity.convertor

import androidx.room.TypeConverter
import com.cube.foodtoseeyou.entity.Category
import com.cube.foodtoseeyou.entity.MealItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealListConverter {
    @TypeConverter
    fun fromCategoryList(category: List<MealItem>): String? {
        if (category == null)
            return null
        else {
            val gson = Gson()
            val type = object : TypeToken<MealItem>() {

            }.type
            return gson.toJson(category, type)
        }
    }

    @TypeConverter
    fun toCategortList(categoryString : String): List<MealItem>? {
        if (categoryString == null)
            return null
        else {
            val gson = Gson()
            val type = object :TypeToken<MealItem>() {

            }.type
            return gson.fromJson(categoryString,type)
        }
    }
}