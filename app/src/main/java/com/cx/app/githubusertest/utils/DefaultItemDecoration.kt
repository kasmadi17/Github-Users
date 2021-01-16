package com.cx.app.githubusertest.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author kasmadi
 * @date 1/15/21
 */
class DefaultItemDecoration(
    private val spanCount: Int = 1,
    private val spacing: Int = 10,
    private val includeEdge: Boolean = false,
    private val onlyTopBottom: Boolean = false
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing - (column * spacing / spanCount)
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) outRect.top = spacing

            outRect.bottom = spacing

        } else if (onlyTopBottom) {

            if (position < spanCount) outRect.top = spacing

            outRect.bottom = spacing

        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1)
            if (position >= spanCount) outRect.top = spacing
        }
    }
}