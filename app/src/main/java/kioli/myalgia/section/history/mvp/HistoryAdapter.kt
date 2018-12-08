package kioli.myalgia.section.history.mvp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kioli.myalgia.R
import kioli.myalgia.section.history.entity.HistoryItemModel
import kotlinx.android.synthetic.main.item_history.view.*

internal class HistoryAdapter(private var dataSet: List<HistoryItemModel>) :
        RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    class MyViewHolder(private val historyView: View) : RecyclerView.ViewHolder(historyView) {
        fun bind(item: HistoryItemModel) {
            historyView.history_mood.text = item.mood.toString()
            historyView.history_weather.text = "${item.location} ${item.temperature}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    fun refreshData(items: List<HistoryItemModel>) {
        dataSet = items
    }

    fun getItemFromPosition(position: Int) = dataSet[position]
}