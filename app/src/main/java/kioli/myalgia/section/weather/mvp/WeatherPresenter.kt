package kioli.myalgia.section.weather.mvp

import kioli.myalgia.common.error.Error
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.Invoker
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.common.mvp.BasePresenter
import kioli.myalgia.section.weather.entity.WeatherModel
import kioli.myalgia.section.weather.interactor.WeatherUseCase
import kioli.myalgia.section.weather.mvp.WeatherContract.Presenter

internal class WeatherPresenter(private val invoker: Invoker,
                                private val getWeather: UseCase<WeatherUseCase.Params, WeatherModel>)
    : BasePresenter<WeatherContract.View>(), Presenter {

    override fun getWeather(forceNew: Boolean) {
        view?.showLoading()
        val params = WeatherUseCase.Params(forceNew)
        invoker.execute(getWeather, params, ::onWeatherArrived)
    }

    private fun onWeatherArrived(result: Either<Error, WeatherModel>) {
        view?.hideLoading()
        result.fold(ifLeft = {
            view?.returnResultWeather(null)
        }, ifRight = {
            view?.returnResultWeather(it)
        })
    }
}