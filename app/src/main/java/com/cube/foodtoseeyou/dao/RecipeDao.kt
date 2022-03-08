package com.cube.foodtoseeyou.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cube.foodtoseeyou.entity.CategoryItems
import com.cube.foodtoseeyou.entity.MealItem

@Dao
interface RecipeDao {
    @Query("Select * from categoryitems order by id DESC")
    suspend fun getAllCategory(): List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryItems: CategoryItems?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealsItem: MealItem?)

    @Query("Delete from categoryitems")
    suspend fun clearDb()

    @Query("Select * from MealItems where categoryName = categoryName order by id DESC")
    suspend fun getSpecificMealList(categoryName: String): List<MealItem>
}