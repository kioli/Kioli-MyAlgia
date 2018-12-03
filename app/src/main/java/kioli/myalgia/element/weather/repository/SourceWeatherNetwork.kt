package kioli.myalgia.element.weather.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.element.weather.entity.WeatherApiModel

/**
 * The data source contracts are part of the domain layer.
 * Their implementations belong to the data layer.
 */
internal interface SourceWeatherNetwork {

    /**
     * Get a new weather
     *
     * @param latitude latitude of the location to fetch the weather for
     * @param longitude longitude of the location to fetch the weather for
     */
    fun getWeather(latitude: Double, longitude: Double): Either<MyError, WeatherApiModel>
}
