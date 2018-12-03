package kioli.myalgia.element.weather.repository

import kioli.myalgia.BuildConfig
import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.Try
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.element.weather.api.WeatherApi
import kioli.myalgia.element.weather.entity.WeatherApiModel

internal class WeatherNetworkDataSource(private val service: WeatherApi) : SourceWeatherNetwork {

    override fun getWeather(latitude: Double, longitude: Double): Either<MyError, WeatherApiModel> =
            Try {
                val latLon = latitude.toString().plus(",").plus(longitude)
                service.loadWeather(BuildConfig.WEATHER_KEY, latLon).execute()
            }.fold(ifFailure = {
                MyError.ServerError.left()
            }, ifSuccess = { response ->
                if (response.isSuccessful) {
                    val body = response.body()!!.let {
                        it.copy(location = it.location,
                                current = it.current.copy(condition = it.current.condition.copy(icon = "http:" + it.current.condition.icon)))
                    }
                    body.right()
                } else WeatherError().left()
            })
}
