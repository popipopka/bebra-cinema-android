package it.bebra.cinema.presentation.catalog.recycle

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FilmCatalogGridItemDecoration(
    private val bottomSpacing: Int,
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val cellWidth = parent.width / spanCount

        outRect.bottom = bottomSpacing

        outRect.left = cellWidth / 16
        outRect.right = outRect.left
    }
}