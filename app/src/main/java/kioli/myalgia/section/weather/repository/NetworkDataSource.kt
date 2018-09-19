package kioli.myalgia.section.weather.repository

import kioli.myalgia.common.error.Error
import kioli.myalgia.common.functional.Either
import kioli.myalgia.section.weather.entity.WeatherModel

/**
 * The data source contracts are part of the domain layer.
 * Their implementations belong to the data layer.
 */
internal interface NetworkDataSource {

    fun getWeather(): Either<Error, WeatherModel>
}
