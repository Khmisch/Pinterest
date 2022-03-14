package com.example.pinterest.model

data class ResultPhotos(
    var total: Int? = null,
    var total_pages: Int? = null,
    var results: ArrayList<Photo>? = null
)