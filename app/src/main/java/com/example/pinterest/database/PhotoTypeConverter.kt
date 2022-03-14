package com.example.pinterest.database

import androidx.room.TypeConverter
import com.example.pinterest.model.Photo
import com.google.gson.Gson

class PhotoTypeConverter {
    @TypeConverter
    fun fromPhotoItem(photoItem: Photo): String {
        return Gson().toJson(photoItem)
    }

    @TypeConverter
    fun toPhotoItem(json: String): Photo {
        return Gson().fromJson(json, Photo::class.java)
    }
}