package kioli.myalgia.common

import android.content.Context
import android.widget.CheckBox

internal class SquareCheckBox constructor(context: Context) : CheckBox(context) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        setMeasuredDimension(width, width)
    }
}