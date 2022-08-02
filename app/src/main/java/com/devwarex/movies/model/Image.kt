package com.devwarex.movies.model

data class Image(
    val aspect_ratio: Double,
    val height: Int,
    val width: Int,
    val file_path: String,
    val vote_average: Double,
    val vote_count: Int
)
