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
    @ColumnInfo(name = "idcategory")
    @SerializedName("idCategory")
    @Expose
    val idcategory: String,
    @ColumnInfo(name = "strcategory")
    @SerializedName("strCategory")
    @Expose
    val strcategory: String,
    @ColumnInfo(name = "strcategorycescription")
    @SerializedName("strCategoryDescription")
    @Expose
    val strcategorydescription: String,
    @ColumnInfo(name = "strcategorythumb")
    @SerializedName("strCategoryThumb")
    @Expose
    val strcategorythumb: String
)