package com.github.kadehar.innopolis_online_cinema.features.films_screen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.kadehar.innopolis_online_cinema.R
import com.github.kadehar.innopolis_online_cinema.base.setThrottledClickListener
import com.github.kadehar.innopolis_online_cinema.databinding.MovieItemBinding
import com.github.kadehar.innopolis_online_cinema.domain.model.Movie

class MoviesAdapter(
    private var movies: List<Movie>,
    private val onItemClick: (movie: Movie) -> Unit,
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MoviesViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie = movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class MoviesViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(movie.posterPath)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_image_not_found)
                .into(binding.moviePoster)

            binding.moviePoster.setThrottledClickListener {
                onItemClick(movie)
            }
        }
    }

    fun updateList(newMovies: List<Movie>) {
        val diffCallback = MoviesDiff(
            oldMoviesList = movies,
            newMoviesList = newMovies
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

        movies = newMovies
    }
}