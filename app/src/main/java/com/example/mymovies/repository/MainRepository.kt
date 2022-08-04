package com.example.mymovies.repository

import com.example.mymovies.networking.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getAllPokemon() = retrofitService.getAllPokemon()
}