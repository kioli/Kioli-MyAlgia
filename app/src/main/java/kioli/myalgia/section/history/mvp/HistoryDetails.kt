package kioli.myalgia.section.history.mvp

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kioli.myalgia.R
import kioli.myalgia.section.history.entity.HistoryItemModel
import kotlinx.android.synthetic.main.history_details.*

internal class HistoryDetails : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.history_details, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item = arguments?.getParcelable<HistoryItemModel>(ARG_HISTORY_ITEM)
        mood_title.text = "MOOD"
        mood_text.text = item?.mood.toString()
    }

    companion object {
        private const val ARG_HISTORY_ITEM = "history model"

        fun getInstance(historyModel: HistoryItemModel): HistoryDetails {
            val fragmentArguments = Bundle()
            fragmentArguments.putParcelable(ARG_HISTORY_ITEM, historyModel)
            return HistoryDetails().apply { arguments = fragmentArguments }
        }
    }
}