package kioli.myalgia.section.pain

import android.content.Context
import android.graphics.drawable.StateListDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CompoundButton
import android.widget.LinearLayout
import kioli.myalgia.R
import kioli.myalgia.common.component.SquareCheckBox

internal class MoodView @JvmOverloads constructor(context: Context,
                                                  attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr), CompoundButton.OnCheckedChangeListener {

    private val moods = arrayListOf(SquareCheckBox(context),
            SquareCheckBox(context),
            SquareCheckBox(context),
            SquareCheckBox(context),
            SquareCheckBox(context))

    init {
        orientation = LinearLayout.HORIZONTAL
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        val arrayMoods = resources.obtainTypedArray(R.array.moods)
        moods.forEachIndexed { index, mood ->
            mood.layoutParams = LayoutParams(0, WRAP_CONTENT, 0.2F)
            mood.background = ContextCompat.getDrawable(context, arrayMoods.getResourceId(index, -1))
            mood.buttonDrawable = StateListDrawable()
            mood.setOnCheckedChangeListener(this)
            addView(mood)
        }
        arrayMoods.recycle()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        moods.forEach { it.setOnCheckedChangeListener(null) }
        moods.filterNot { it == buttonView }.forEach { it.isChecked = false }
        moods.forEach { it.setOnCheckedChangeListener(this) }
    }
}