package it.bebra.cinema.presentation.catalog.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import it.bebra.cinema.R
import it.bebra.cinema.databinding.ItemFilmCatalogBinding
import it.bebra.domain.model.FilmListModel

class CatalogFilmListAdapter(
    private val items: List<FilmListModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class FilmViewHolder(
        private val binding: ItemFilmCatalogBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: FilmListModel) {
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

        private fun formatDuration(duration: Int): String {
            val minutes: Int = duration % 60
            val hours: Int = (duration - minutes) / 60;

            if (hours == 0) {
                return "$minutes min"
            }

            if (minutes == 0) {
                return "$hours h"
            }

            return "$hours h $minutes min"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vhBinding =
            ItemFilmCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FilmViewHolder(vhBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FilmViewHolder) {
            holder.bind(items[position])
        }
    }
}