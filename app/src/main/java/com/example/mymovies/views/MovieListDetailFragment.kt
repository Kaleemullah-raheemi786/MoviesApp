package com.example.mymovies.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mypokmon.databinding.FragmentPokemonDetailListBinding
import com.squareup.picasso.Picasso

class MovieListDetailFragment : Fragment() {
    lateinit var binding: FragmentPokemonDetailListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPokemonDetailListBinding.inflate(layoutInflater, container, false)

        binding.name.text = requireArguments().getString("name")
        binding.textCategory.text = requireArguments().getString("category")
        binding.textDesc.text = requireArguments().getString("desc")
        Picasso.get().load(requireArguments().getString("imageUrl")).into(binding.imageView)

        return binding.root
    }
}