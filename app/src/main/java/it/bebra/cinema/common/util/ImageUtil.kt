package it.bebra.cinema.common.util

import android.widget.ImageView
import coil.load
import it.bebra.cinema.R

fun loadMovieImage(url: String, view: ImageView) {
    view.load(url) {
        crossfade(true)
        crossfade(1500)
        placeholder(R.drawable.download_film_poster)
        error(R.drawable.error_film_poster)
    }
}