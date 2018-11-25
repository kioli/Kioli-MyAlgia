package kioli.myalgia.section.history.mvp

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedRelativeLayout
import kioli.myalgia.element.weather.mvp.HistoryContract
import kioli.myalgia.section.history.di.historyViewModules
import kioli.myalgia.section.history.entity.HistoryItemUiModel
import kotlinx.android.synthetic.main.view_history.view.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

internal class HistoryView @JvmOverloads constructor(context: Context,
                                                     attrs: AttributeSet? = null,
                                                     defStyleAttr: Int = 0) :
        InjectedRelativeLayout(context, attrs, defStyleAttr), HistoryContract.View {

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
    }

    override fun linearLayoutModule() = Kodein.Module("module history view", false) {
        import(historyViewModules())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.attachView(this)
        presenter.getHistoryItems()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }

    override fun showHistory(historyItems: List<HistoryItemUiModel>) {
        history_empty.visibility = GONE
        viewAdapter.refreshData(historyItems)
    }

    override fun showEmptyState() {
        history_empty.visibility = VISIBLE
        history_loading.visibility = GONE
    }

    override fun showLoading() {
        history_empty.visibility = GONE
        history_loading.visibility = VISIBLE
    }

    override fun hideLoading() {
        history_empty.visibility = GONE
        history_loading.visibility = VISIBLE
    }

    override fun requestHistoryItems() {
        // do nothing
    }
}