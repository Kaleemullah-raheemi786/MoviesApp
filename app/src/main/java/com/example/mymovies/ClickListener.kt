package com.example.mymovies

interface ClickListener<T> {
    fun onItemClick(data: T)
}