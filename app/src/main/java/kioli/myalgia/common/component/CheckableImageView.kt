package kioli.myalgia.common.component

import android.content.Context
import android.view.View
import android.widget.Checkable
import android.widget.ImageView

class CheckableImageView(context: Context) : ImageView(context), Checkable {

    private val checkedStateSet = intArrayOf(android.R.attr.state_checked)
    private var checked = false

    override fun isChecked(): Boolean = checked

    override fun setChecked(isChecked: Boolean) {
        if (isChecked != checked) {
            checked = isChecked
            refreshDrawableState()
        }
    }

    override fun toggle() {
        isChecked = !checked
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            View.mergeDrawableStates(drawableState, checkedStateSet)
        }
        return drawableState
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        invalidate()
    }
}
