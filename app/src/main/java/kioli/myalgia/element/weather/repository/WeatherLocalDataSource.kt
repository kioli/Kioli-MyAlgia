package kioli.myalgia.element.weather.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.element.weather.db.WeatherDb
import kioli.myalgia.element.weather.entity.WeatherModel

internal class WeatherLocalDataSource(private val db: WeatherDb) : SourceWeatherLocal {

    override fun getWeather(): Either<MyError, WeatherModel> {
        if (db.weatherModelDao().getAll().isNotEmpty()) {
            return db.weatherModelDao().getAll().first().right()
        }
        return WeatherError().left()
    }

    override fun saveWeather(weather: WeatherModel) {
        db.weatherModelDao().insert(weather)
    }
}
