package kioli.myalgia.section.settings

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kioli.myalgia.R

internal class SettingView @JvmOverloads constructor(context: Context,
                                                     attrs: AttributeSet? = null,
                                                     defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = LinearLayout.HORIZONTAL
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        val radioGroup = RadioGroup(context).apply { orientation = HORIZONTAL }
        context.theme.obtainStyledAttributes(attrs, R.styleable.SettingView, 0, 0)
                .apply {
                    try {
                        val title = getString(R.styleable.SettingView_title)?.let { makeTitle(it) }
                        val option1 = getString(R.styleable.SettingView_option1)?.let { makeOption(it) }
                        val option2 = getString(R.styleable.SettingView_option2)?.let { makeOption(it) }
                        addViews(title, radioGroup, option1, option2)
                    } finally {
                        recycle()
                    }
                }
    }

    private fun makeTitle(title: String): TextView = TextView(context).apply {
        layoutParams = LayoutParams(0, WRAP_CONTENT, 1F)
        setTextAppearance(context, R.style.SettingText)
        setVerticalGravity(Gravity.CENTER)
        text = title
    }

    private fun makeOption(optionText: String): RadioButton = RadioButton(context).apply {
        text = optionText
    }

    private fun addViews(title: TextView?,
                         radioGroup: RadioGroup,
                         option1: RadioButton?,
                         option2: RadioButton?) {
        title?.let { addView(it) }
        addView(radioGroup)
        option1?.let { radioGroup.addView(it) }
        option2?.let { radioGroup.addView(it) }
    }
}