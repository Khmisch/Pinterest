package com.example.pinterest.model

import com.example.pinterest.model.Photo

data class RelatedPhotos(
    var total: Int? = null,
    var results: ArrayList<Photo>? = null
)