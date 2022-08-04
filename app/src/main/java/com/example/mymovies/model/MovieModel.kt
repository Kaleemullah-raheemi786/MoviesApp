package com.example.mypokmon

import java.io.Serializable
data class MovieModel(
    val category: String? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val desc: String? = null

) : Serializable


