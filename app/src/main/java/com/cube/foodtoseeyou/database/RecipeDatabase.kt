package com.cube.foodtoseeyou.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cube.foodtoseeyou.dao.RecipeDao
import com.cube.foodtoseeyou.entity.*
import com.cube.foodtoseeyou.entity.convertor.CategoryListConverter
import com.cube.foodtoseeyou.entity.convertor.MealListConverter

@Database(
    entities = [Recipe::class, Category::class, CategoryItems::class, Meal::class, MealItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CategoryListConverter::class, MealListConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    companion object {
        var recipeDB: RecipeDatabase? = null

        @Synchronized
        fun getDB(context: Context): RecipeDatabase {
            if (recipeDB == null) {
                recipeDB = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build()
            }
            return recipeDB!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}