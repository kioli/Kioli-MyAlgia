package kioli.myalgia.element.weather.interactor

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.element.weather.entity.WeatherDbModel
import kioli.myalgia.element.weather.repository.WeatherRepository

internal class WeatherUseCase(private val repository: WeatherRepository)
    : UseCase<WeatherUseCase.Params, WeatherDbModel>() {

    override fun run(params: Params): Either<MyError, WeatherDbModel> =
            repository.getWeather(params.latitude, params.longitude)

    data class Params(val latitude: Double, val longitude: Double)
}