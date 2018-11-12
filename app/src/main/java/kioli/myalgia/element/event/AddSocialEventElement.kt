package kioli.myalgia.element.event

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import kioli.myalgia.R
import kioli.myalgia.common.component.ElementContainer

class AddSocialEventElement @JvmOverloads constructor(context: Context,
                                                      callback: () -> Unit,
                                                      attrs: AttributeSet? = null,
                                                      defStyleAttr: Int = 0) :
        ElementContainer(context, attrs, defStyleAttr) {

    init {
        val text = TextView(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            text = resources.getString(R.string.event_addition, resources.getText(R.string.event_name_social).toString().toLowerCase())
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        }
        setOnClickListener { callback.invoke() }
        addView(text)
    }
}
