package com.github.kadehar.innopolis_online_cinema.features.movie_card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.kadehar.innopolis_online_cinema.base.formatDate
import com.github.kadehar.innopolis_online_cinema.base.genresToString
import com.github.kadehar.innopolis_online_cinema.base.loadImage
import com.github.kadehar.innopolis_online_cinema.databinding.FragmentMovieCardBinding
import com.github.kadehar.innopolis_online_cinema.domain.model.Movie

class MovieCardFragment : Fragment() {
    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: Movie) = MovieCardFragment().apply {
            arguments = bundleOf(Pair(MOVIE_KEY, movie))
        }
    }

    private var _binding: FragmentMovieCardBinding? = null
    private val binding get() = _binding!!

    private val movie: Movie by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieCardBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cardPoster.loadImage(movie.posterPath)
            cardMovieTitle.text = movie.originalTitle
            cardMovieVotes.text = movie.voteAverage.toString()
            cardMovieReleaseDate.text = formatDate(movie.releaseDate)
            cardMovieGenres.text = genresToString(movie.genres)
            cardMovieOverview.text = movie.overview
        }
    }
}