package com.example.pinterest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterest.R
import com.example.pinterest.model.Photos
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MessagesMessagesAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var photoList = Photos()

    @SuppressLint("NotifyDataSetChanged")
    fun addPhotos(photoList: Photos) {
        this.photoList.addAll(photoList)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_message_view, parent, false)
        return PinsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {         val photoItem = photoList[position]
            val photoUrl = photoItem.urls!!.thumb
            val photoColor = photoItem.color
            val s1 = photoItem.alt_description
            val s2 = photoItem.description
            val s3 = photoItem.user!!.name

            if (holder is PinsViewHolder) {
                holder.tv_fullname.text = getDescription(s1, s2, s3)
//                Glide.with(context).load(photoUrl)
//                    .placeholder(ColorDrawable(Color.parseColor(photoColor)))
//                    .error(ColorDrawable(Color.parseColor(photoColor)))
//                    .into(holder.iv_pin);
                Picasso.get().load(photoUrl)
                    .placeholder(ColorDrawable(Color.parseColor(photoColor)))
                    .into(holder.iv_profile)
            }
    }

    class PinsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        var tv_fullname: TextView = view.findViewById(R.id.tv_fullname)
        var bt_messages: Button = view.findViewById(R.id.bt_messages)
    }

    private fun getDescription(s1: Any?, s2: String?, s3: String?): String {
        return when {
            s1 != null -> s1.toString()
            s2 != null -> s2.toString()
            else -> s3!!
        }
    }
}