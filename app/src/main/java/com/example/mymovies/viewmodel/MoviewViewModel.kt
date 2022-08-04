package com.example.mymovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.repository.MainRepository
import com.example.mypokmon.MovieModel
import kotlinx.coroutines.*

class MoviewViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val pokemonList = MutableLiveData<List<MovieModel>>()
    private var handleJob: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        handleJob?.cancel()
    }

    fun getAllMovieS() {
        handleJob = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllPokemon()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    pokemonList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }
}
