package kioli.myalgia.section.weather.repository

import kioli.myalgia.common.error.Error
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.Try
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.section.weather.api.WeatherApi
import kioli.myalgia.section.weather.entity.WeatherModel

class WeatherNotFound : Error.FeatureError()

internal class WeatherNetworkDataSource(private val service: WeatherApi) : NetworkDataSource {

    private val key = "e44d02fff19244c3b30205216181609"
    private val city = "Amsterdam"

    override fun getWeather(): Either<Error, WeatherModel> =
            Try {
                service.loadWeather(key, city).execute()
            }.fold(ifFailure = {
                Error.ServerError.left()
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
