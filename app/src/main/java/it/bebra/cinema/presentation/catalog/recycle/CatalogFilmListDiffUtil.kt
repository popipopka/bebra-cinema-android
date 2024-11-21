package it.bebra.cinema.presentation.catalog.recycle

import androidx.recyclerview.widget.DiffUtil
import it.bebra.domain.model.MovieListModel

class CatalogFilmListDiffUtil(
    private val oldList: List<MovieListModel>,
    private val newList: List<MovieListModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}