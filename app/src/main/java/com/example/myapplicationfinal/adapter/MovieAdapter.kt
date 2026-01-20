package com.example.myapplicationfinal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationfinal.databinding.ItemMovieBinding
import com.example.myapplicationfinal.model.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.binding.movieTitle.text = movie.title
        holder.binding.movieOverview.text = movie.overview

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(holder.binding.movieImage)

        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = movies.size
}
