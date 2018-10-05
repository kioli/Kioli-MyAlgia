package kioli.myalgia.common.ext

import android.content.Context

const val sharedPreferences = "myalgia sp"

fun Context.addToSharedPref(key: String, value: String) {
    getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE).edit().putString(key, value).apply()
}

fun Context.readSharedPref(key: String): String?
        = getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE).getString(key, null)