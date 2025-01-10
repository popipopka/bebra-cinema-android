package it.bebra.cinema.app.presentation.catalog.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.app.common.ui.decoration.HorizontalCenteringItemDecoration
import it.bebra.cinema.app.common.ui.decoration.LastItemBottomSpacingItemDecoration
import it.bebra.cinema.app.common.ui.decoration.SpacingItemDecoration
import it.bebra.cinema.app.presentation.catalog.recycle.CatalogMovieListAdapter
import it.bebra.cinema.app.presentation.catalog.viewmodel.CatalogViewModel
import it.bebra.cinema.app.presentation.login.activity.LoginActivity
import it.bebra.cinema.app.presentation.movie.activity.MovieActivity
import it.bebra.cinema.databinding.FragmentCatalogBinding
import it.bebra.cinema.domain.Resource.Empty
import it.bebra.cinema.domain.Resource.Success
import it.bebra.cinema.domain.Resource.Unauthorized

@AndroidEntryPoint
class CatalogFragment : Fragment() {
    companion object {
        private const val DEFAULT_SPAN_COUNT = 2
    }

    private lateinit var binding: FragmentCatalogBinding
    private val vm: CatalogViewModel by viewModels()

    private var isMoviesLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentCatalogBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupListeners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.loadMovies()
    }

    private fun startMovieActivity(id: Int) {
        val intent = Intent(context, MovieActivity::class.java)
        intent.putExtra("movieId", id)

        startActivity(intent)
    }

    private fun setupRecyclerView() {
        this.binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this.context, DEFAULT_SPAN_COUNT)

            addItemDecoration(SpacingItemDecoration(0, 0, 0, 40))
            addItemDecoration(HorizontalCenteringItemDecoration(DEFAULT_SPAN_COUNT, 16))
            addItemDecoration(LastItemBottomSpacingItemDecoration(86))

            adapter = CatalogMovieListAdapter(::startMovieActivity)
        }
    }

    private fun setupListeners() {
        binding.recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (isMoviesLoading) {
                    return
                }

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if ((totalItemCount <= (lastVisibleItem + 1))) {
                    isMoviesLoading = true
                    vm.loadMovies()
                }
            }
        })

        binding.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.equals(vm.query)) {
                        searchMovies(query)

                        binding.searchView.clearFocus()

                        return false
                    }

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = true
            })

            setOnQueryTextFocusChangeListener { v, hasFocus ->
                if (!hasFocus && this.query.isNullOrEmpty() && !vm.query.isNullOrEmpty()) {
                    Log.d("Search", "пусто")
                    searchMovies(null)
                }
            }
        }
    }

    private fun searchMovies(query: String?) {
        vm.resetState()
        vm.query = query
        vm.loadMovies()
    }

    private fun setupObservers() {
        vm.resultFlow.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    (binding.recyclerView.adapter as CatalogMovieListAdapter)
                        .submitList(vm.movies)

                    isMoviesLoading = false
                }

                is Empty -> (binding.recyclerView.adapter as CatalogMovieListAdapter)
                    .submitList(vm.movies)

                is Unauthorized -> startLoginActivity()

                else -> Unit
            }
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this.context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intent)
    }
}
