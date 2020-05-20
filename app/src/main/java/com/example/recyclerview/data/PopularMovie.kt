package com.example.recyclerview.data

data class PopularMovies(
    val results: List<PopularMovie>
)

data class PopularMovie(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)
