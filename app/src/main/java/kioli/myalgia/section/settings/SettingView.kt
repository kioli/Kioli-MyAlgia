package kioli.myalgia.section.settings

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kioli.myalgia.R
import kioli.myalgia.common.ext.addToSharedPref
import kioli.myalgia.common.ext.readSharedPref
import kioli.myalgia.section.settings.entity.Preference

internal class SettingView constructor(context: Context, private val preference: Preference)
    : LinearLayout(context) {

    init {
        orientation = LinearLayout.VERTICAL
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        addTitle()
        addOptions()
    }

    private fun addTitle() {
        val title = TextView(context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            setTextAppearance(context, R.style.SettingText)
            setVerticalGravity(Gravity.CENTER)
            text = preference.title
        }
        addView(title)
    }

    private fun addOptions() {
        val radioGroup = RadioGroup(context).apply {
            orientation = HORIZONTAL
            val emptySpace = makeEmptySpace()
            val option1 = makeOption(preference.option1)
            val option2 = makeOption(preference.option2)
            addView(emptySpace)
            addView(option1)
            addView(option2)
            assignPreviouslySavedValue(option1, option2)
            setOnCheckedChangeListener { group, checkedId ->
                context.addToSharedPref(preference.title, group.findViewById<RadioButton>(checkedId).text.toString())
            }
        }
        addView(radioGroup)
    }

    private fun makeEmptySpace() = View(context).apply {
        layoutParams = LayoutParams(0, WRAP_CONTENT, 1F)
    }

    private fun makeOption(optionText: String) = RadioButton(context).apply {
        layoutParams = LayoutParams(0, WRAP_CONTENT, 1F)
        text = optionText
        setPadding(0, 0, resources.getDimensionPixelSize(R.dimen.preference_padding), 0)
    }

    private fun assignPreviouslySavedValue(option1: RadioButton, option2: RadioButton) {
        val selectedPref = context.readSharedPref(preference.title)
        when (selectedPref) {
            preference.option2 -> option2.isChecked = true
            else -> option1.isChecked = true
        }
    }
}
