package kioli.myalgia.element.event

import android.content.Context
import android.util.AttributeSet
import android.view.View
import kioli.myalgia.R
import kioli.myalgia.common.component.ElementContainer

class SocialEventElement @JvmOverloads constructor(context: Context,
                                                   attrs: AttributeSet? = null,
                                                   defStyleAttr: Int = 0) :
        ElementContainer(context, attrs, defStyleAttr) {

    init {
        setTitle(resources.getString(R.string.event_name_social))
        val view = inflate(context, R.layout.view_event, null)
        addView(view)
    }
}