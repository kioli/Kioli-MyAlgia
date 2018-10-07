package kioli.myalgia.section.settings

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kioli.myalgia.R
import kioli.myalgia.common.ext.readSettingFromSharedPref
import kioli.myalgia.common.ext.storeSettingToSharedPref
import kioli.myalgia.section.settings.entity.Setting

@SuppressLint("ViewConstructor")
internal class SettingView constructor(context: Context, setting: Setting)
    : LinearLayout(context) {

    private val title: TextView = TextView(context)
    private val radioGroup: RadioGroup = RadioGroup(context)

    init {
        orientation = LinearLayout.VERTICAL
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        addTitle(setting.title)
        addOptions(setting)
    }

    private fun addTitle(titleText: String) {
        title.apply {
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            setTextAppearance(context, R.style.SettingText)
            setVerticalGravity(Gravity.CENTER)
            text = titleText
        }
        addView(title)
    }

    private fun addOptions(setting: Setting) {
        radioGroup.apply {
            orientation = HORIZONTAL
            setting.options.forEach {
                val radioButton = makeOption(it.title)
                addView(radioButton)
            }
            setOnCheckedChangeListener { group, checkedId ->
                setting.options
                        .firstOrNull { it == group.findViewById<RadioButton>(checkedId).text }
                        ?.let { context.storeSettingToSharedPref(setting, it) }
            }
        }
        assignPreviouslySavedValue(setting)
        addView(radioGroup)
    }

    private fun makeOption(optionText: String) = RadioButton(context).apply {
        layoutParams = LayoutParams(0, WRAP_CONTENT, 1F)
        text = optionText
        setPadding(0, 0, resources.getDimensionPixelSize(R.dimen.preference_padding), 0)
    }

    private fun assignPreviouslySavedValue(setting: Setting) {
        val selectedOption = context.readSettingFromSharedPref(setting)
        (0 until radioGroup.childCount).forEach {
            val radioButton = radioGroup.getChildAt(it) as RadioButton
            if (radioButton.text == selectedOption.title) {
                radioButton.isChecked = true
                return@forEach
            }
        }
    }
}
