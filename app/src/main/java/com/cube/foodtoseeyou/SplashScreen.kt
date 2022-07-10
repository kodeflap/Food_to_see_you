package com.cube.foodtoseeyou

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cube.foodtoseeyou.database.RecipeDatabase
import com.cube.foodtoseeyou.entity.Category
import com.cube.foodtoseeyou.entity.Meal
import com.cube.foodtoseeyou.entity.MealItem
import com.cube.foodtoseeyou.interfaces.GetDataService
import com.cube.foodtoseeyou.retrofit.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.EasyPermissions.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreen : BaseActivity(), RationaleCallbacks, PermissionCallbacks {
    private var READ_STORAGE_PERMISSION = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        readStorageTask()

        getStartedBtn.setOnClickListener {
            var intent = Intent(this@SplashScreen, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun getCategories() {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object : Callback<Category> {

            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {
                for (arr in response.body()!!.categorieitems!!) {
                    getMeal(arr.strcategory)
                }
                insertDataToRoom(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                // loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashScreen, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getMeal(categoryName: String) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getMealList(categoryName)
        call.enqueue(object : Callback<Meal> {
            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                insertMealDataToRoom(categoryName, response.body())
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                Toast.makeText(this@SplashScreen, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun insertDataToRoom(category: Category?) {
        launch {
            this.let {
                for (arr in category!!.categorieitems!!) {
                    RecipeDatabase.getDatabase(this@SplashScreen).recipeDao().insertCategory(arr)
                }
            }
        }
    }

    fun insertMealDataToRoom(categoryName: String, meal: Meal?) {
        launch {
            this.let {
                for (arr in meal!!.mealsItem!!) {
                    var mealitemModel = MealItem(
                        arr.id,
                        arr.idMeal,
                        categoryName,
                        arr.strMeal,
                        arr.strMealThumb
                    )
                    RecipeDatabase.getDatabase(this@SplashScreen).recipeDao().insertMeal(mealitemModel)
                }
                getStartedBtn.visibility = View.VISIBLE
            }
        }
    }

    fun clearDatabase() {
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashScreen).recipeDao().clearDb()
            }
        }
    }

    private fun hasReadStroagePermssion(): Boolean {
        return hasPermissions(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun readStorageTask() {
        if (hasReadStroagePermssion()) {
            getCategories()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "This app needs to access to your storage",
                READ_STORAGE_PERMISSION,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onRationaleAccepted(requestCode: Int) {

    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}