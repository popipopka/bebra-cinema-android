package it.bebra.cinema.presentation.catalog.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.common.ui.HorizontalCenteringItemDecoration
import it.bebra.cinema.common.ui.SpacingItemDecoration
import it.bebra.cinema.databinding.FragmentCatalogBinding
import it.bebra.cinema.presentation.catalog.recycle.CatalogMovieListAdapter
import it.bebra.cinema.presentation.catalog.viewmodel.CatalogViewModel
import it.bebra.cinema.presentation.movie.activity.MovieActivity
import it.bebra.domain.model.MovieListModel

@AndroidEntryPoint
class CatalogFragment : Fragment() {
    private lateinit var binding: FragmentCatalogBinding

    private val viewModel: CatalogViewModel by lazy {
        ViewModelProvider(this)[CatalogViewModel::class]
    }

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

        if (binding.recyclerView.adapter?.itemCount == 0) {
            viewModel.loadFilms()
        }
    }

    private fun startMovieActivity(id: Int) {
        val intent = Intent(context, MovieActivity::class.java)

        intent.putExtra("movieId", id)

        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val recyclerView = this.binding.recyclerView

        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)

        recyclerView.addItemDecoration(SpacingItemDecoration(0, 0, 0, 40))
        recyclerView.addItemDecoration(HorizontalCenteringItemDecoration(2, 16))

        recyclerView.adapter = CatalogMovieListAdapter(::startMovieActivity)
    }

    private fun setupObservers() {
        viewModel.resultResponses.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as CatalogMovieListAdapter).submitList(it)
        }
    }
}
