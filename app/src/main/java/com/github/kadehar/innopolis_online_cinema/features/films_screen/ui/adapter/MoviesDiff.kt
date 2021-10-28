package com.github.kadehar.innopolis_online_cinema.features.films_screen.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.kadehar.innopolis_online_cinema.domain.model.Movie

class MoviesDiff(
    private val oldMoviesList: List<Movie>,
    private val newMoviesList: List<Movie>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldMoviesList.size

    override fun getNewListSize(): Int = newMoviesList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMoviesList[oldItemPosition] == newMoviesList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMoviesList[oldItemPosition] == newMoviesList[newItemPosition]
}