package kioli.myalgia.section.weather.repository

import android.content.SharedPreferences
import android.util.Log
import kioli.myalgia.common.error.Error
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.section.weather.entity.CurrentWeather
import kioli.myalgia.section.weather.entity.WeatherModel

internal class WeatherLocalDataSource(private val pref: SharedPreferences) : LocalDataSource {

    private val weatherKey = "weather key pref"

    override fun getWeather(): Either<Error, WeatherModel> {
        pref.getString(weatherKey, null)?.let {
            return WeatherModel(CurrentWeather(it.toFloat(), 0.0F, 0.0F)).right()
        }
        Log.e("MyAlgia", "Weather not found in the local cache")
        return WeatherNotFound().left()
    }

    override fun saveWeather(weather: WeatherModel) {
        pref.edit().putString(weatherKey, weather.current.temperature.toString()).apply()
    }
}
