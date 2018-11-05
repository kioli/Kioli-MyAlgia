package kioli.myalgia.section.home

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.ScrollView
import kioli.myalgia.R
import kioli.myalgia.element.pain.MoodElement
import kioli.myalgia.element.weather.mvp.WeatherElement

class HomeView @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null,
                                         defStyleAttr: Int = 0) :
        ScrollView(context, attrs, defStyleAttr) {

    init {
        setBackgroundColor(ContextCompat.getColor(context, R.color.bg))

        val container = createElementsContainer()
        container.addView(MoodElement(context))
        container.addView(WeatherElement(context))
        addView(container)
    }

    private fun createElementsContainer() = LinearLayout(context).apply {
        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        orientation = LinearLayout.VERTICAL
    }
}