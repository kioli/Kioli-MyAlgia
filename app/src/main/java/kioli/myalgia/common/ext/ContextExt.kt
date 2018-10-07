package kioli.myalgia.common.ext

import android.content.Context
import kioli.myalgia.section.settings.entity.Option
import kioli.myalgia.section.settings.entity.Setting

const val sharedPreferences = "myalgia sp"

fun Context.storeSettingToSharedPref(setting: Setting, option: Option) {
    getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE).edit().putString(setting.title, option.title).apply()
}

fun Context.readSettingFromSharedPref(setting: Setting): Option {
    val optionText = getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE).getString(setting.title, null)
    return setting.options.first { it.title == optionText }
}