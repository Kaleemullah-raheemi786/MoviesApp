package com.example.mymovies.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mypokmon.MovieModel
import com.example.mypokmon.R
import com.example.mymovies.adapter.ListMovieAdapter
import com.example.mypokmon.databinding.FragmentPokemonListBinding
import com.example.mymovies.networking.RetrofitService
import com.example.mymovies.repository.MainRepository
import com.example.mymovies.viewmodel.MoviewViewModel
import com.example.mymovies.viewmodel.PokemonViewModelFactory

class MovieListFragment : Fragment(){
    lateinit var viewModel: MoviewViewModel
    private val adapter = ListMovieAdapter()
    lateinit var binding: FragmentPokemonListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPokemonListBinding.inflate(layoutInflater, container, false)

        init()

        return binding.root
    }

    fun init() {

        val retrofitService = RetrofitService.getInstance()
        val repository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            PokemonViewModelFactory(repository)
        )[MoviewViewModel::class.java]

        binding.recycleView.adapter = adapter

        viewModel.pokemonList.observe(viewLifecycleOwner)
        { results ->
            setMovies(results)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner)
        {
            Toast.makeText(context, "Something went wrong :(", Toast.LENGTH_LONG).show()
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }

        viewModel.getAllMovieS()
    }

    private fun setMovies(results: List<MovieModel>) {
        adapter.setData(results)
        adapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movies: MovieModel) {
                findNavController().navigate(
                    R.id.action_pokemonListFragment_to_pokemonDetailListFragment,
                    Bundle().apply {
                        putString("name", movies.name)
                        putString("category", movies.category)
                        putString("desc", movies.desc)
                        putString("imageUrl", movies.imageUrl)
                    })
            }
        })
    }
}

