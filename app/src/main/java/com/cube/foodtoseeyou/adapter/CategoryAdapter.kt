package com.cube.foodtoseeyou.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cube.foodtoseeyou.R
import com.cube.foodtoseeyou.entity.CategoryItems
import com.cube.foodtoseeyou.entity.Recipe
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var categoryList = ArrayList<CategoryItems>()
    var listener: OnItemClickListener? = null
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    context = parent.context
        return ViewHolder(
            (LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.dish_name.text = categoryList[position].strCategory
        Glide.with(context!!).load(categoryList[position].strCategoryThumb).into(holder.itemView.dish_img)
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(categoryList[position].strCategory)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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