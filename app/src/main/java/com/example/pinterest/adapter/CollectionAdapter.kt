package com.example.pinterest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypinterest.model.MyCollection
import com.example.pinterest.R
import com.example.pinterest.fragment.SearchFragment
import com.example.pinterest.model.Photos
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class CollectionAdapter (var context: SearchFragment, var items: ArrayList<MyCollection>, val search: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_view, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder)
            holder.bind(position)
    }

    override fun getItemCount() = items.size

    inner class PhotoViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val iv_photo: ImageView = view.findViewById(R.id.iv_pin)
        val tv_search_title: TextView = view.findViewById(R.id.tv_name)
        val fm_container: FrameLayout = view.findViewById(R.id.fl_search)

        fun bind(position: Int){
            val topic = items[position]

            Glide
                .with(view)
                .load(topic.url)
                .error(R.color.defalt_white)
                .into(iv_photo)

            tv_search_title.text = topic.title

            fm_container.setOnClickListener {
                search.invoke(topic.title)
            }
        }
    }
}