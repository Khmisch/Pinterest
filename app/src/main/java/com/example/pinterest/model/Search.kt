package com.example.pinterest.model

import com.example.pinterest.model.Photo

data class Search (
    val total: Long?,
    val totalPages: Long?,
    val results: List<Photo?>
)