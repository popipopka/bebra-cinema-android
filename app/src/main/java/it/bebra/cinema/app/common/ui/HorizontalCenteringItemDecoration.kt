package it.bebra.cinema.app.common.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalCenteringItemDecoration(
    private val spanCount: Int,
    private val k: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val cellWidth = parent.width / spanCount

        outRect.left = cellWidth / k
        outRect.right = outRect.left
    }
}