package kioli.myalgia.section.weather.interactor

import kioli.myalgia.common.error.Error
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.common.repository.CachePolicy
import kioli.myalgia.section.weather.entity.WeatherModel
import kioli.myalgia.section.weather.repository.WeatherRepository

internal class WeatherUseCase(private val repository: WeatherRepository)
    : UseCase<WeatherUseCase.Params, WeatherModel>() {

    override fun run(params: Params): Either<Error, WeatherModel> =
            when (params.forceNew) {
                true -> repository.getWeather(CachePolicy.NetworkFirst)
                false -> repository.getWeather(CachePolicy.LocalFirst)
            }

    data class Params(val forceNew: Boolean)
}