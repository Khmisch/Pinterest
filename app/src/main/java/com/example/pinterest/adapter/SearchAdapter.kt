package com.example.pinterest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterest.R
import com.example.pinterest.activity.MainActivity
import com.example.pinterest.fragment.DetailsFragment
import com.example.pinterest.fragment.HomeFragment
import com.example.pinterest.fragment.SearchFragment
import com.example.pinterest.model.Photo
import com.google.android.material.imageview.ShapeableImageView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class SearchAdapter  (var context: SearchFragment, var items: ArrayList<Photo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun addPhotos(items: ArrayList<Photo>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_view, parent, false)
            return PinsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val photoItem = items[position]
            val photoUrl = photoItem.urls!!.thumb
            val photoColor = photoItem.color
            val s1 = photoItem.alt_description
            val s2 = photoItem.description
            val s3 = photoItem.user!!.name

            if (holder is PinsViewHolder) {
//                holder.tv_description.text = getDescription(s1, s2, s3)
//                Glide.with(context).load(photoUrl)
//                    .placeholder(ColorDrawable(Color.parseColor(photoColor)))
//                    .error(ColorDrawable(Color.parseColor(photoColor)))
//                    .into(holder.iv_pin);
                Picasso.get().load(photoUrl).placeholder(ColorDrawable(Color.parseColor(photoColor)))
                    .into(holder.iv_pin)

                holder.iv_pin.setOnClickListener {

                }
            }
    }


    class PinsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var iv_pin: ShapeableImageView = view.findViewById(R.id.iv_pin)
        var tv_description: TextView = view.findViewById(R.id.tv_description)
        var iv_more: ImageView = view.findViewById(R.id.iv_more)
    }

    private fun getDescription(s1: Any?, s2: String?, s3: String?): String {
        return when {
            s1 != null -> s1.toString()
            s2 != null -> s2.toString()
            else -> "Photo was made by $s3"
        }
    }
}