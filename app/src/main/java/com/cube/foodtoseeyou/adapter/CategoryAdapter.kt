package com.cube.foodtoseeyou.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cube.foodtoseeyou.R
import com.cube.foodtoseeyou.entity.CategoryItems
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.RecipeViewHolder>() {
    var categoryList = ArrayList<CategoryItems>()
    var listener: OnItemClickListener? = null
    var context: Context? = null

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        context = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.dish_name.text = categoryList[position].strcategory
        Glide.with(context!!).load(categoryList[position].strcategorythumb).into(holder.itemView.dish_img)
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(categoryList[position].strcategory)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    //data setting function
    fun setData(categoryData: List<CategoryItems>) {
        categoryList = categoryData as ArrayList<CategoryItems>
    }

    fun setClickListener(listener1: OnItemClickListener) {
        listener = listener1
    }

    interface OnItemClickListener {
        fun onClicked(categoryName: String)
    }
}