package com.github.kadehar.innopolis_online_cinema.features.films_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kadehar.innopolis_online_cinema.R
import com.github.kadehar.innopolis_online_cinema.databinding.FragmentMoviesListBinding
import com.github.kadehar.innopolis_online_cinema.features.films_screen.ui.adapter.MoviesAdapter
import com.github.kadehar.innopolis_online_cinema.features.movie_card.ui.MovieCardFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment() {
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel by viewModel<MoviesViewModel>()
    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(movies = emptyList()) { movie ->
            moviesViewModel.processUiEvent(UiEvent.OnPosterClick(movie))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = moviesAdapter
        }

        moviesViewModel.viewState.observe(viewLifecycleOwner, ::render)
        moviesViewModel.singleLiveEvent.observe(viewLifecycleOwner, ::onSingleEvent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(viewState: ViewState) {
        binding.pbMovies.isGone = !viewState.isLoading
        moviesAdapter.updateList(viewState.movies)
    }

    private fun onSingleEvent(event: SingleEvent) {
        when (event) {
            is SingleEvent.OpenMovieCard -> {
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    .add(R.id.moviesContainer, MovieCardFragment.newInstance(event.movie))
                    .addToBackStack("movies")
                    .commit()
            }
        }
    }
}
