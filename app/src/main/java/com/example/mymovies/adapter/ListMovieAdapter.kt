package com.example.mymovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokmon.MovieModel
import com.example.mypokmon.databinding.ItemLayoutBinding

import com.squareup.picasso.Picasso

class ListMovieAdapter : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback
    var listPokemon = mutableListOf<MovieModel>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(poke: List<MovieModel>){
        listPokemon.clear()
        listPokemon.addAll(poke)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(var binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokes: MovieModel) {
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(pokes)
            }
            binding.apply {
                name.text = pokes.name
                Picasso.get().load(pokes.imageUrl).into(image)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            ItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    interface OnItemClickCallback {
        fun onItemClicked(pokes: MovieModel)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listPokemon[position])
    }

    override fun getItemCount(): Int = listPokemon.size
}

