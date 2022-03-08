package com.cube.foodtoseeyou.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CategoryItems")
data class CategoryItems(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo(name = "idCategory")
    @SerializedName("idCategory")
    @Expose
    val idCategory: String,
    @ColumnInfo(name = "strCategory")
    @SerializedName("strCategory")
    @Expose
    val strCategory: String,
    @ColumnInfo(name = "strCategoryDescription")
    @SerializedName("strCategoryDescription")
    @Expose
    val strCategoryDescription: String,
    @ColumnInfo(name = "strCategoryThumb")
    @SerializedName("strCategoryThumb")
    @Expose
    val strCategoryThumb: String
)