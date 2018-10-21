package kioli.myalgia.main

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kioli.myalgia.R
import kioli.myalgia.section.weather.mvp.WeatherView

internal class SectionsAdapter : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val layout = when(position) {
            0 -> WeatherView(container.context)
            else -> inflater.inflate(R.layout.view_history, container, false) as ViewGroup
        }
        layout.tag = Section.values()[position].tag
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View, obj: Any) = view == obj

    override fun getCount() = Section.values().size

}