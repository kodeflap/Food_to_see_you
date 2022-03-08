package com.cube.foodtoseeyou

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.cube.foodtoseeyou.entity.MealResponse
import com.cube.foodtoseeyou.interfaces.GetData
import com.cube.foodtoseeyou.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DetailActivity : BaseActivity() {
    var youtubeLink = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var id = intent.getStringExtra("id")

        getSpecificItem(id!!)

        back.setOnClickListener {
            var uri = Uri.parse(youtubeLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun getSpecificItem(id: String) {
        val service = RetrofitClient.retrofitInstance!!.create(GetData::class.java)
        val call = service.getSpecificItem(id)
        call.enqueue(object : retrofit2.Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                Glide.with(this@DetailActivity).load(response.body())
                tv_category.text = response.body()!!.meals[0].strMeal
                var ingredient =
                    "${response.body()!!.meals[0].strIngredient1}  ${response.body()!!.meals[0].strMeasure1}\n" +
                            "${response.body()!!.meals[0].strIngredient2}  ${response.body()!!.meals[0].strMeasure2}\n" +
                            "${response.body()!!.meals[0].strIngredient3}  ${response.body()!!.meals[0].strMeasure3}\n" +
                            "${response.body()!!.meals[0].strIngredient4}  ${response.body()!!.meals[0].strMeasure4}\n" +
                            "${response.body()!!.meals[0].strIngredient5}  ${response.body()!!.meals[0].strMeasure5}\n" +
                            "${response.body()!!.meals[0].strIngredient6}  ${response.body()!!.meals[0].strMeasure6}\n" +
                            "${response.body()!!.meals[0].strIngredient7}  ${response.body()!!.meals[0].strMeasure7}\n" +
                            "${response.body()!!.meals[0].strIngredient8}  ${response.body()!!.meals[0].strMeasure8}\n" +
                            "${response.body()!!.meals[0].strIngredient9}  ${response.body()!!.meals[0].strMeasure9}\n" +
                            "${response.body()!!.meals[0].strIngredient10}  ${response.body()!!.meals[0].strMeasure10}\n" +
                            "${response.body()!!.meals[0].strIngredient11}  ${response.body()!!.meals[0].strMeasure11}\n" +
                            "${response.body()!!.meals[0].strIngredient12}  ${response.body()!!.meals[0].strMeasure12}\n" +
                            "${response.body()!!.meals[0].strIngredient13}  ${response.body()!!.meals[0].strMeasure13}\n" +
                            "${response.body()!!.meals[0].strIngredient14}  ${response.body()!!.meals[0].strMeasure14}\n" +
                            "${response.body()!!.meals[0].strIngredient15}  ${response.body()!!.meals[0].strMeasure15}\n" +
                            "${response.body()!!.meals[0].strIngredient16}  ${response.body()!!.meals[0].strMeasure16}\n" +
                            "${response.body()!!.meals[0].strIngredient17}  ${response.body()!!.meals[0].strMeasure17}\n" +
                            "${response.body()!!.meals[0].strIngredient18}  ${response.body()!!.meals[0].strMeasure18}\n" +
                            "${response.body()!!.meals[0].strIngredient19}  ${response.body()!!.meals[0].strMeasure19}\n" +
                            "${response.body()!!.meals[0].strIngredient20}  ${response.body()!!.meals[0].strMeasure20}\n"

                tv_ingredients.text = ingredient
                tv_instruction.text = response.body()!!.meals[0].strInstructions

                if (response.body()!!.meals[0].strSource != null) {
                    youtubeLink = response.body()!!.meals[0].strSource as String
                } else {
                    btn_youtube.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Something went wrong", Toast.LENGTH_LONG)
                    .show()
            }

        })
    }
}