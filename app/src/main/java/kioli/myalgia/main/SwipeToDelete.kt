package kioli.myalgia.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kioli.myalgia.R

abstract class SwipeToDelete(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val deleteIcon = AppCompatResources.getDrawable(context, R.drawable.ic_delete)
    private val background = ColorDrawable()
    private val backgroundColor = ContextCompat.getColor(context, R.color.delete)
    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }

    override fun getMovementFlags(recyclerView: RecyclerView,
                                  viewHolder: RecyclerView.ViewHolder): Int =
            when (viewHolder.adapterPosition) {
                0, 1, 2 -> 0
                else -> super.getMovementFlags(recyclerView, viewHolder)
            }

    override fun onMove(recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder): Boolean = false

    override fun onChildDraw(c: Canvas,
                             recyclerView: RecyclerView,
                             viewHolder: RecyclerView.ViewHolder,
                             dX: Float,
                             dY: Float,
                             actionState: Int,
                             isCurrentlyActive: Boolean) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top
        val isCanceled = dX == 0f && !isCurrentlyActive

        if (isCanceled) {
            clearCanvas(c, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        // Draw the red delete background
        background.color = backgroundColor
        background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
        background.draw(c)

        // Calculate position of delete icon
        deleteIcon?.let {
            val deleteIconTop = itemView.top + (itemHeight - it.intrinsicHeight) / 2
            val deleteIconMargin = (itemHeight - it.intrinsicHeight) / 2
            val deleteIconLeft = itemView.right - deleteIconMargin - it.intrinsicWidth
            val deleteIconRight = itemView.right - deleteIconMargin
            val deleteIconBottom = deleteIconTop + it.intrinsicHeight

            // Draw the delete icon
            deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            deleteIcon.draw(c)
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        c.drawRect(left, top, right, bottom, clearPaint)
    }
}