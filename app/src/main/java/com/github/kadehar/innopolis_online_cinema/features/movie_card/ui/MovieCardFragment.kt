package com.github.kadehar.innopolis_online_cinema.features.movie_card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.kadehar.innopolis_online_cinema.base.loadImage
import com.github.kadehar.innopolis_online_cinema.databinding.FragmentMovieCardBinding
import com.github.kadehar.innopolis_online_cinema.domain.model.Movie

class MovieCardFragment : Fragment() {
    private var _binding: FragmentMovieCardBinding? = null
    private val binding get() = _binding!!

    private val currMovie: Movie by lazy {
        requireArguments().getParcelable("movie")!!
    }

    companion object {
        fun newInstance(movie: Movie) = MovieCardFragment().apply {
            arguments = bundleOf(Pair("movie", movie))
        }
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
            cardPoster.loadImage(currMovie.posterPath)
            cardMovieTitle.text = currMovie.originalTitle
        }
    }
}