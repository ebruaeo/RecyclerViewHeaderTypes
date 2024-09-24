package com.example.recyclerviewheader
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StickyHeaderItemDecoration(
    private val adapter: ItemAdapter // Pass in the adapter
) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        // Get the layout manager (in this case, LinearLayoutManager)
        val layoutManager = parent.layoutManager as? LinearLayoutManager ?: return

        // Find the position of the first visible item
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

        // If there's no visible item, return
        if (firstVisiblePosition == RecyclerView.NO_POSITION) return

        // Find the header position for the first visible item
        val currentHeaderPosition = findHeaderPositionForItem(firstVisiblePosition)

        // If no header is found, return
        if (currentHeaderPosition == -1) return

        // Get the current header view
        val currentHeaderView = parent.findViewHolderForAdapterPosition(currentHeaderPosition)?.itemView ?: return

        // Prepare to draw the header
        val child = parent.findViewHolderForAdapterPosition(firstVisiblePosition)?.itemView ?: return

        // Find the next header position and determine if the current header should be pushed up
        val nextHeaderPosition = findHeaderPositionForItem(firstVisiblePosition + 1)
        val isHeaderPushedUp = nextHeaderPosition != -1 &&
                parent.findViewHolderForAdapterPosition(nextHeaderPosition)?.itemView?.let {
                    it.top <= currentHeaderView.height
                } ?: false

        // Draw the header at the top or push it out of view based on the next header
        drawHeader(c, parent, currentHeaderView, isHeaderPushedUp, child.top)
    }


    // Finds the position of the current header for a given item
    private fun findHeaderPositionForItem(itemPosition: Int): Int {
        for (i in itemPosition downTo 0) {
            if (adapter.getItemViewType(i) == ItemAdapter.VIEW_TYPE_HEADER) {
                return i
            }
        }
        return -1
    }

    private fun drawHeader(
        canvas: Canvas,
        parent: RecyclerView,
        headerView: View,
        isHeaderPushedUp: Boolean,
        nextHeaderTop: Int
    ) {
        val headerHeight = headerView.height
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        // Determine where to draw the header (sticky at top or pushed up by next header)
        val top = if (isHeaderPushedUp) {
            nextHeaderTop - headerHeight
        } else {
            parent.paddingTop
        }

        // Translate the canvas and draw the header
        canvas.save()
        canvas.translate(left.toFloat(), top.toFloat())
        headerView.draw(canvas)
        canvas.restore()
    }

    // Optional: Adjust item offsets for spacing if needed
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position != RecyclerView.NO_POSITION && adapter.getItemViewType(position) == ItemAdapter.VIEW_TYPE_HEADER) {
            outRect.top = view.height
        }
    }
}
