package kioli.myalgia.common.ext

import android.content.Context
import kioli.myalgia.section.settings.entity.*

const val sharedPreferences = "myalgia sp"

fun Context.storeSettingOptionToSharedPref(setting: Setting, option: Option) {
    getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE).edit().putInt(setting.title, setting.options.indexOf(option)).apply()
}

fun Context.readSettingOptionFromSharedPref(setting: Setting): Option {
    val optionIndex = getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE).getInt(setting.title, -1)
    return setting.options.elementAtOrNull(optionIndex) ?: setting.options[0]
}