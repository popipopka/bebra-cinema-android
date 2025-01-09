package it.bebra.cinema.app.presentation.movie.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.R
import it.bebra.cinema.app.common.ui.SpacingItemDecoration
import it.bebra.cinema.app.common.util.formatDuration
import it.bebra.cinema.app.common.util.loadMovieImage
import it.bebra.cinema.app.presentation.login.activity.LoginActivity
import it.bebra.cinema.app.presentation.movie.adapter.pager.MonthViewPagerAdapter
import it.bebra.cinema.app.presentation.movie.adapter.recycle.MovieSessionsListAdapter
import it.bebra.cinema.app.presentation.movie.internal.month.fragment.MonthFragment
import it.bebra.cinema.app.presentation.movie.viewmodel.MovieViewModel
import it.bebra.cinema.app.presentation.movie.viewmodel.MovieViewModel.MonthlySessions
import it.bebra.cinema.app.presentation.movie.viewmodel.MovieViewModel.Session
import it.bebra.cinema.app.presentation.purchase.activity.PurchaseActivity
import it.bebra.cinema.databinding.ActivityMovieBinding
import it.bebra.cinema.domain.Resource.Success
import it.bebra.cinema.domain.dto.movie.MovieDetailResponse
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    companion object {
        private const val DEFAULT_DESCRIPTION_MAX_LINES = 4
    }

    private lateinit var binding: ActivityMovieBinding
    private val vm: MovieViewModel by viewModels()

    private var isDescriptionHiding = true
    private var selectedMonthFragmentIndex: Int? = null

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

            vm.loadMovie(movieId)
            vm.loadMovieSession(movieId)
        }
    }

    private fun startPurchaseActivity(sessionId: Int) {
        val intent = Intent(this, PurchaseActivity::class.java)

        intent.putExtra("sessionId", sessionId)

        startActivity(intent)
    }

    private fun setupObservers() {
        vm.resultFlow.observe(this) {
            when (it) {
                is Success -> fillInformationAboutMovie(it.data)
                else -> Unit
            }
        }

        vm.sessionResultFlow.observe(this) {
            when (it) {
                is Success -> {
                    lifecycleScope.launch {
                        createMonthlyFragments(vm.monthlySessionsList)
                        setupSessionRecycleView()
                    }
                }

                else -> Unit
            }
        }
    }

    private fun createMonthlyFragments(data: List<MonthlySessions>) {
        binding.monthlySession.visibility = View.VISIBLE

        val viewPagerAdapter = MonthViewPagerAdapter(this)
        binding.monthlyViewPager.adapter = viewPagerAdapter

        for ((i, e) in data.withIndex()) {
            viewPagerAdapter.addFragment(MonthFragment(i, e, ::showSessions))
        }
    }

    private fun showSessions(monthFragmentIndex: Int, data: List<Session>) {
        val recyclerAdapter = MovieSessionsListAdapter(::startPurchaseActivity)
        recyclerAdapter.submitList(data)

        binding.apply {
            sessionsRecycleView.adapter = recyclerAdapter
            labelSessions.visibility = View.VISIBLE
        }

        selectedMonthFragmentIndex = monthFragmentIndex

        (binding.monthlyViewPager.adapter as MonthViewPagerAdapter)
            .getFragments().forEach { e ->
                if (e.index != selectedMonthFragmentIndex) e.removeSelectionFromSelectedDay()
            }
    }

    private fun setupSessionRecycleView() {
        binding.sessionsRecycleView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            addItemDecoration(SpacingItemDecoration(10, 0, 10, 0))
        }

        binding.sessionsRecycleView.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun fillInformationAboutMovie(data: MovieDetailResponse) {
        binding.apply {
            topBar.title = data.name

            data.posterUrl?.let {
                loadMovieImage(it, moviePoster)
            }

            movieName.text = data.name
            movieGenres.text = data.genres.joinToString(", ")
            movieDurationValue.text = formatDuration(data.duration)
            movieYearValue.text = data.year.toString()

            movieDescription.text = data.description

            if (movieDescription.lineCount > DEFAULT_DESCRIPTION_MAX_LINES) {
                descriptionStateArrow.visibility = View.VISIBLE
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
                binding.movieDescription.maxLines = DEFAULT_DESCRIPTION_MAX_LINES
                descriptionStateArrow.setImageResource(R.drawable.ic_expand_arrow)
            }
        }

        isDescriptionHiding = !isDescriptionHiding
    }
}