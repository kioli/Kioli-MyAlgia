package kioli.myalgia.section.history

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView

class HistoryView @JvmOverloads constructor(context: Context,
                                            attrs: AttributeSet? = null,
                                            defStyleAttr: Int = 0) :
        RelativeLayout(context, attrs, defStyleAttr) {

    init {
        val placeholder = TextView(context, attrs, defStyleAttr).apply { text = "LAALA" }
        addView(placeholder)
    }
}