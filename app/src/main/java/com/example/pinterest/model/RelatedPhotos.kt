package com.example.pinterest.model

data class RelatedPhotos(
    var total: Int? = null,
    var results: ArrayList<Photo>? = null
)