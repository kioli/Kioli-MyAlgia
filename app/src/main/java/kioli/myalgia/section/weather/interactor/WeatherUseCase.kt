package kioli.myalgia.section.weather.interactor

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.common.repository.CachePolicy
import kioli.myalgia.section.weather.entity.WeatherModel
import kioli.myalgia.section.weather.repository.WeatherRepository

internal class WeatherUseCase(private val repository: WeatherRepository)
    : UseCase<WeatherUseCase.Params, WeatherModel>() {

    override fun run(params: Params): Either<MyError, WeatherModel> =
            when (params.forceNew) {
                true -> repository.getWeather(CachePolicy.NetworkFirst, params.latitude, params.longitude)
                false -> repository.getWeather(CachePolicy.LocalOnly, params.latitude, params.longitude)
            }

    data class Params(val forceNew: Boolean, val latitude: Double, val longitude: Double)
}