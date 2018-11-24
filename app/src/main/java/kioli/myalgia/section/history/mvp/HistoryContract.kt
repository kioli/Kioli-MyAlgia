package kioli.myalgia.element.weather.mvp

import kioli.myalgia.common.mvp.PresenterI
import kioli.myalgia.common.mvp.ViewI
import kioli.myalgia.section.history.entity.HistoryItemUiModel

internal interface HistoryContract {

    interface View : ViewI {
        /**
         * Show the stored history items in the RecyclerView
         *
         * @param historyItems the stored items
         */
        fun showHistory(historyItems: List<HistoryItemUiModel>)

        /**
         * Show empty state when no history items are stored already
         */
        fun showEmptyState()

        /**
         * Show the loading screen in the HistoryView
         */
        fun showLoading()

        /**
         * Hide the loading screen in the HistoryView
         */
        fun hideLoading()

        /**
         * Request history items from the DB
         */
        fun requestHistoryItems()
    }

    interface Presenter : PresenterI<View> {

        /**
         * Get history items from the DB
         */
        fun getHistoryItems()
    }
}