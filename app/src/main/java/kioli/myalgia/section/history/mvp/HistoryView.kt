package kioli.myalgia.section.history.mvp

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kioli.myalgia.R
import kioli.myalgia.common.component.RecyclerItemClickListener
import kioli.myalgia.common.di.InjectedRelativeLayout
import kioli.myalgia.element.weather.mvp.HistoryContract
import kioli.myalgia.section.history.di.historyViewModules
import kioli.myalgia.section.history.entity.HistoryItemModel
import kotlinx.android.synthetic.main.view_history.view.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

internal class HistoryView(activity: AppCompatActivity) :
        InjectedRelativeLayout(activity), HistoryContract.View {

    private val detailsTag = "details fragment dialog"
    private val presenter by instance<HistoryContract.Presenter>()

    private val viewAdapter: HistoryAdapter by lazy {
        HistoryAdapter(listOf())
    }

    private val viewManager: RecyclerView.LayoutManager by lazy {
        LinearLayoutManager(context)
    }

    init {
        View.inflate(context, R.layout.view_history, this)
        history_list.layoutManager = viewManager
        history_list.adapter = viewAdapter
        history_list.addOnItemTouchListener(object : RecyclerItemClickListener(
                context,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        HistoryDetails.getInstance(viewAdapter.getItemFromPosition(position))
                                .show(activity.supportFragmentManager, detailsTag)
                    }
                }) {}
        )
    }

    override fun linearLayoutModule() = Kodein.Module("module history view", false) {
        import(historyViewModules())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.attachView(this)
        loadHistory()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }

    override fun showHistory(historyItems: List<HistoryItemModel>) {
        history_empty.visibility = GONE
        viewAdapter.refreshData(historyItems)
        viewAdapter.notifyDataSetChanged()
    }

    override fun showEmptyState() {
        history_empty.visibility = VISIBLE
    }

    override fun showLoading() {
        history_loading.visibility = VISIBLE
    }

    override fun hideLoading() {
        history_loading.visibility = GONE
    }

    override fun loadHistory() {
        presenter.getHistoryItems()
    }
}
