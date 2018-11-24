package kioli.myalgia.main

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import kioli.myalgia.section.history.mvp.HistoryView
import kioli.myalgia.section.home.HomeView

internal class SectionsAdapter : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = when (position) {
            0 -> HomeView(container.context)
            else -> HistoryView(container.context)
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