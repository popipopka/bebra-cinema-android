package it.bebra.cinema.presentation.movie.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import it.bebra.cinema.R
import it.bebra.cinema.common.util.formatDuration
import it.bebra.cinema.common.util.loadMovieImage
import it.bebra.cinema.databinding.ActivityMovieBinding
import it.bebra.cinema.presentation.movie.viewmodel.MovieViewModel

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    private var isDescriptionHiding = true

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleIntent(intent)

        setupObservers()
        setupListeners()
    }

    private fun handleIntent(intent: Intent) {
        intent.extras!!.let {
            val movieId: Int = it.getInt("movieId")

            viewModel.loadMovie(movieId)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() {
        viewModel.resultResponses.observe(this) { movie ->
            binding.apply {
                binding.topBar.title = movie.name

                loadMovieImage(movie.posterUrl, moviePoster)

                movieName.text = movie.name
                movieGenres.text = movie.genres.joinToString(", ")
                movieDurationValue.text = formatDuration(movie.duration)
                movieYearValue.text = movie.year.toString()
                movieProducerValue.text = movie.producer

                movieDescription.text = movie.description
            }
        }
    }

    private fun setupListeners() {
        binding.apply {
            descriptionStateArrow.setOnClickListener {
                changeDescriptionState()
            }

            topBar.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun changeDescriptionState() {
        with(binding) {
            if (isDescriptionHiding) {
                binding.movieDescription.maxLines = Int.MAX_VALUE
                descriptionStateArrow.setImageResource(R.drawable.ic_hide_arrow)

            } else {
                binding.movieDescription.maxLines = 2
                descriptionStateArrow.setImageResource(R.drawable.ic_expand_arrow)
            }
        }

        isDescriptionHiding = !isDescriptionHiding
    }
}