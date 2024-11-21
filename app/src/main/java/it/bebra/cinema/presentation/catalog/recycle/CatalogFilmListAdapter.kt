package it.bebra.cinema.presentation.catalog.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import it.bebra.cinema.R
import it.bebra.cinema.common.util.formatDuration
import it.bebra.cinema.databinding.ItemFilmCatalogBinding
import it.bebra.domain.model.MovieListModel

class CatalogFilmListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data: MutableList<MovieListModel> = mutableListOf()

    class FilmViewHolder(
        private val binding: ItemFilmCatalogBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: MovieListModel) {
            film.posterUrl?.let {
                binding.filmPoster.load(film.posterUrl) {
                    crossfade(true)
                    crossfade(1500)
                    placeholder(R.drawable.download_film_poster)
                    error(R.drawable.error_film_poster)
                }
            }

            binding.filmTitleLabel.text = film.name

            binding.filmDurationLabel.text = formatDuration(film.duration)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vhBinding =
            ItemFilmCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FilmViewHolder(vhBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FilmViewHolder) {
            holder.bind(data[position])
        }
    }

    fun addData(data: List<MovieListModel>) {
        val diffCallback = CatalogFilmListDiffUtil(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.data.addAll(data)

        diffResult.dispatchUpdatesTo(this)
    }
}