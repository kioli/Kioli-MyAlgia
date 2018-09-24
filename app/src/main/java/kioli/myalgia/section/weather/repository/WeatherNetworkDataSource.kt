package kioli.myalgia.section.weather.repository

import kioli.myalgia.BuildConfig
import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.Try
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.section.weather.api.WeatherApi
import kioli.myalgia.section.weather.entity.WeatherModel

class WeatherNotFound : MyError.FeatureError()

internal class WeatherNetworkDataSource(private val service: WeatherApi) : NetworkDataSource {

    override fun getWeather(latitude: Double, longitude: Double): Either<MyError, WeatherModel> =
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
                } else WeatherNotFound().left()
            })
}
