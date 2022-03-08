package com.cube.foodtoseeyou.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cube.foodtoseeyou.entity.convertor.CategoryListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo(name = "categoryItems")
    @SerializedName("categories")
    @TypeConverters(CategoryListConverter::class)
    @Expose
    val categoriesItems: List<CategoryItems>? = null
)
