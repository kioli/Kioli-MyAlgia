package kioli.myalgia.section.weather.repository

import kioli.myalgia.common.error.Error
import kioli.myalgia.common.functional.Either
import kioli.myalgia.section.weather.entity.WeatherModel

/**
 * The data source contracts are part of the domain layer.
 * Their implementations belong to the data layer.
 */
internal interface NetworkDataSource {

    /**
     * Get a new weather
     *
     * @param latitude latitude of the location to fetch the weather for
     * @param longitude longitude of the location to fetch the weather for
     */
    fun getWeather(latitude: Double, longitude: Double): Either<Error, WeatherModel>
}
