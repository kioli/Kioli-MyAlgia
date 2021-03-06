package kioli.myalgia.section.home

import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedRelativeLayout
import kioli.myalgia.element.Element
import kioli.myalgia.element.weather.mvp.WeatherElement

internal class HomeView(activity: AppCompatActivity) : InjectedRelativeLayout(activity) {

    private val recyclerView: RecyclerView by lazy {
        RecyclerView(context).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            layoutManager = viewManager
            adapter = viewAdapter
            overScrollMode = View.OVER_SCROLL_NEVER
        }
    }

    private val viewAdapter: ElementsAdapter by lazy {
        ElementsAdapter(activity, elements) { viewAdapter.addElement(Element.EVENT_SOCIAL) }
    }

    private val viewManager: RecyclerView.LayoutManager by lazy {
        LinearLayoutManager(context)
    }

    private val elements = listOf(
            Element.ADD_EVENT_SOCIAL,
            Element.PAIN,
            Element.WEATHER)

    init {
        setBackgroundColor(ContextCompat.getColor(context, R.color.bg))

        val swipeHandler = object : SwipeToDelete(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as ElementsAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        addView(recyclerView)
    }

    fun requestWeather(forceNewWeather: Boolean) {
        val weatherElement = viewManager.findViewByPosition(2)
        if (weatherElement != null && weatherElement is WeatherElement) {
            weatherElement.requestWeather(forceNewWeather)
        }
    }
}