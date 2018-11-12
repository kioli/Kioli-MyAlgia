package kioli.myalgia.section.home

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.ScrollView
import kioli.myalgia.R
import kioli.myalgia.element.Element
import kioli.myalgia.main.SwipeToDelete

class HomeView @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null,
                                         defStyleAttr: Int = 0) :
        ScrollView(context, attrs, defStyleAttr) {

    private val elements = listOf(
            Element.ADD_EVENT_SOCIAL,
            Element.PAIN,
            Element.WEATHER)

    private val recyclerView: RecyclerView by lazy {
        RecyclerView(context).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private val viewAdapter: ElementsAdapter by lazy {
        ElementsAdapter(elements) {viewAdapter.addElement(Element.EVENT_SOCIAL)}
    }

    private val viewManager: RecyclerView.LayoutManager by lazy {
        LinearLayoutManager(context)
    }

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
}