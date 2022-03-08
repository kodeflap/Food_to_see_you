package com.cube.foodtoseeyou

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cube.foodtoseeyou.adapter.CategoryAdapter
import com.cube.foodtoseeyou.adapter.SubCategoryAdapter
import com.cube.foodtoseeyou.database.RecipeDatabase
import com.cube.foodtoseeyou.entity.CategoryItems
import com.cube.foodtoseeyou.entity.MealItem
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {
    //array list initialization
    var categoryList = ArrayList<CategoryItems>()
    var subCategoryList = ArrayList<MealItem>()

    //adapter initialization
    var categoryAdapter = CategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromDB()

        categoryAdapter.setClickListener(onClicked)
        subCategoryAdapter.setClickListener(onClickedSub)

    }
    private val onClicked = object : CategoryAdapter.OnItemClickListener {
        override fun onClicked(categoryName: String) {
            getMealFromDB(categoryName)
        }
    }

    private val onClickedSub = object : SubCategoryAdapter.OnItemClickListner {
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getMealFromDB(categoryName: String) {
        launch {
            this.let {
                var category = RecipeDatabase.getDB(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                subCategoryList = category as ArrayList<MealItem>
                subCategoryAdapter.setData(subCategoryList)
                dish_subcategory_rv.layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                dish_subcategory_rv.adapter = categoryAdapter
            }
        }
    }

    private fun getDataFromDB() {
       launch {
            this.let {
                var category = RecipeDatabase.getDB(this@HomeActivity).recipeDao().getAllCategory()
                categoryList = category as ArrayList<CategoryItems>
                categoryList.reverse()
                getMealFromDB(categoryList[0].strCategory)
                categoryAdapter.setData(categoryList)
                dish_rv.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                dish_rv.adapter = categoryAdapter

            }
        }
    }
}