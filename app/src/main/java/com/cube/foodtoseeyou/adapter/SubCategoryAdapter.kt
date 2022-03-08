package com.cube.foodtoseeyou.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cube.foodtoseeyou.R
import com.cube.foodtoseeyou.entity.MealItem
import kotlinx.android.synthetic.main.category_sub_item.view.*


class SubCategoryAdapter : RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {
    var subCategoryList = ArrayList<MealItem>()
    var listener: SubCategoryAdapter.OnItemClickListner? = null
    var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            (LayoutInflater.from(parent.context).inflate(R.layout.category_sub_item, parent, false))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.sub_dish_name.text = subCategoryList[position].strMeal
        Glide.with(context!!).load(subCategoryList[position].strMealThumb)
            .into(holder.itemView.sub_dish_img)
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(subCategoryList[position].idMeal)
        }
    }

    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    //data setting fuunction
    fun setData(subCategoryData: List<MealItem>) {
        subCategoryList = subCategoryData as ArrayList<MealItem>
    }

    fun setClickListener(listener1: SubCategoryAdapter.OnItemClickListner) {
        listener = listener1
    }

    interface OnItemClickListner {
        fun onClicked(id: String)
    }
}

