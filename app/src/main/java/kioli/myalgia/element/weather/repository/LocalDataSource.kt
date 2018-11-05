package kioli.myalgia.element.weather.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.element.weather.entity.WeatherModel

/**
 * The data source contracts are part of the domain layer.
 * Their implementations belong to the data layer.
 */
internal interface LocalDataSource {

    fun getWeather(): Either<MyError, WeatherModel>

    fun saveWeather(weather: WeatherModel)
}
