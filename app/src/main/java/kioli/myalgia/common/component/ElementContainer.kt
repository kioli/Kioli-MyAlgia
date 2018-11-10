package kioli.myalgia.common.component

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import kioli.myalgia.R

abstract class ElementContainer @JvmOverloads constructor(context: Context,
                                                          attrs: AttributeSet? = null,
                                                          defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {

    private val title by lazy {
        TextView(context, attrs, defStyleAttr).apply {
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            val padding = resources.getDimensionPixelOffset(R.dimen.element_title_padding)
            setPadding(padding, 0, 0, padding)
            visibility = View.GONE
            setTextColor(ContextCompat.getColor(context, R.color.element_title))
        }
    }

    init {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER_VERTICAL
        background = ContextCompat.getDrawable(context, android.R.drawable.dialog_holo_light_frame)
        val padding = resources.getDimensionPixelOffset(R.dimen.element_padding)
        this.setPadding(padding, padding, padding, padding)
        this.addView(title)
    }

    protected fun setTitle(text: String) {
        title.visibility = View.VISIBLE
        title.text = text
    }
}