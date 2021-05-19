package com.example.boomtown.network

import com.squareup.moshi.Json

/**
 * Character is automatically parsed by moshi through JSon tags
 *
 */
data class Character (
    val name: String,
    val height: String,
    val mass: String,
    @Json(name = "birth_year") val birthYear: String,
    @Json(name = "films") val films:List<String>
)
{
    val numFilms = films.size
}

/**
 * Record is only used to get the count of people ids
 */
data class Record (
    val count: String
)