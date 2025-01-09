package it.bebra.cinema.app.presentation.catalog.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import it.bebra.cinema.app.common.util.formatDuration
import it.bebra.cinema.app.common.util.loadMovieImage
import it.bebra.cinema.app.presentation.catalog.recycle.CatalogMovieListAdapter.MovieViewHolder
import it.bebra.cinema.databinding.ItemFilmCatalogBinding
import it.bebra.cinema.domain.dto.movie.MovieListResponse

class CatalogMovieListAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<MovieListResponse, MovieViewHolder>(DiffCallback()) {

    class MovieViewHolder(
        private val binding: ItemFilmCatalogBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieListResponse) {
            movie.posterUrl?.let {
                loadMovieImage(it, binding.moviePoster)
            }

            binding.filmTitleLabel.text = movie.name
            binding.filmDurationLabel.text = formatDuration(movie.duration)

            binding.moviePoster.setOnClickListener {
                onClick(movie.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val vhBinding =
            ItemFilmCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(vhBinding, onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieListResponse>() {

        override fun areItemsTheSame(
            oldItem: MovieListResponse,
            newItem: MovieListResponse
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieListResponse,
            newItem: MovieListResponse
        ): Boolean =
            oldItem == newItem
    }
}