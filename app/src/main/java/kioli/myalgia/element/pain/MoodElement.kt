package kioli.myalgia.element.pain

import android.content.Context
import android.graphics.drawable.StateListDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CompoundButton
import android.widget.LinearLayout
import kioli.myalgia.R
import kioli.myalgia.common.component.ElementContainer
import kioli.myalgia.common.component.SquareCheckBox

internal class MoodElement @JvmOverloads constructor(context: Context,
                                                     attrs: AttributeSet? = null,
                                                     defStyleAttr: Int = 0) :
        ElementContainer(context, attrs, defStyleAttr), CompoundButton.OnCheckedChangeListener {

    private val moods = arrayListOf(SquareCheckBox(context),
            SquareCheckBox(context),
            SquareCheckBox(context),
            SquareCheckBox(context),
            SquareCheckBox(context))

    init {
        setTitle("Feeling")
        val content = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            addMoodsToElement(this)
        }
        addView(content)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        moods.forEach { it.setOnCheckedChangeListener(null) }
        moods.filterNot { it == buttonView }.forEach { it.isChecked = false }
        moods.forEach { it.setOnCheckedChangeListener(this) }
    }

    private fun addMoodsToElement(layout: LinearLayout) {
        val arrayMoods = resources.obtainTypedArray(R.array.moods)
        val moodPadding = resources.getDimensionPixelOffset(R.dimen.mood_icon_padding)
        moods.forEachIndexed { index, mood ->
            mood.layoutParams = LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f).apply {
                setMargins(moodPadding, 0, moodPadding, 0)
            }
            mood.background = ContextCompat.getDrawable(context, arrayMoods.getResourceId(index, -1))
            mood.buttonDrawable = StateListDrawable()
            mood.setOnCheckedChangeListener(this)
            layout.addView(mood)
        }
        arrayMoods.recycle()
    }
}
