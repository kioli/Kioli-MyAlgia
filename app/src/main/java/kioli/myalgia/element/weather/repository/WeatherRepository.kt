package kioli.myalgia.element.weather.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.element.weather.entity.WeatherDbModel
import kioli.myalgia.element.weather.mapper.WeatherApiDbMapper
import timber.log.Timber

internal class WeatherRepository(
        private val networkDataSource: SourceWeatherNetwork,
        private val mapper: WeatherApiDbMapper) {

    fun getWeather(latitude: Double, longitude: Double): Either<MyError, WeatherDbModel> {
        return networkDataSource.getWeather(latitude, longitude).fold(
                {
                    Timber.w("error loading weather from network: $it")
                    WeatherError().left()
                },
                {
                    val weatherDbModel = mapper.mapToLeft(it)
                    weatherDbModel.right()
                }
        )
    }
}