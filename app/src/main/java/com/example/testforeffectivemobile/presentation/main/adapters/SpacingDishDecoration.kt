package com.example.testforeffectivemobile.presentation.main.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingDishDecoration(
    private val spanCount: Int,
    private val itemSpacing: Int,
    private val lineSpacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = itemSpacing - column * itemSpacing / spanCount
            outRect.right = (column + 1) * itemSpacing / spanCount
            if (position < spanCount) outRect.top = lineSpacing
            outRect.bottom = lineSpacing
        } else {
            outRect.left = column * itemSpacing / spanCount
            outRect.right = itemSpacing - (column + 1) * itemSpacing / spanCount
            if (position >= spanCount) outRect.top = lineSpacing
        }
    }
}