package com.example.pinterest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pins_table")
open class Pin(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var photoItem: Photo
)