package kioli.myalgia.section.home

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kioli.myalgia.common.component.ElementContainer
import kioli.myalgia.element.Element
import kioli.myalgia.element.event.AddSocialEventElement
import kioli.myalgia.element.event.SocialEventElement
import kioli.myalgia.element.pain.MoodElement
import kioli.myalgia.element.weather.mvp.WeatherElement
import java.io.InvalidClassException

internal class ElementsAdapter(private val activity: AppCompatActivity,
                               private var dataSet: List<Element>,
                               private val callbackAddEventSocial: () -> Unit) :
        RecyclerView.Adapter<ElementsAdapter.MyViewHolder>() {

    class MyViewHolder(val element: ElementContainer) : RecyclerView.ViewHolder(element)

    override fun getItemViewType(position: Int) = dataSet[position].ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementsAdapter.MyViewHolder =
            when (viewType) {
                Element.ADD_EVENT_SOCIAL.ordinal -> MyViewHolder(AddSocialEventElement(activity, callbackAddEventSocial))
                Element.EVENT_SOCIAL.ordinal -> MyViewHolder(SocialEventElement(activity))
                Element.PAIN.ordinal -> MyViewHolder(MoodElement(activity))
                Element.WEATHER.ordinal -> MyViewHolder(WeatherElement(activity))
                else -> throw InvalidClassException("Type $viewType not found")
            }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {}

    override fun getItemCount() = dataSet.size

    fun addElement(data: Element) {
        dataSet += data
        notifyItemInserted(dataSet.size - 1)
    }

    fun removeAt(position: Int) {
        dataSet = dataSet.filterIndexed { index, _ -> index != position }
        notifyItemRemoved(position)
    }
}