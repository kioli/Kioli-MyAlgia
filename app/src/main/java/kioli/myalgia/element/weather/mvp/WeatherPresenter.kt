package kioli.myalgia.element.weather.mvp

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.Invoker
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.common.mvp.BasePresenter
import kioli.myalgia.element.weather.entity.WeatherModel
import kioli.myalgia.element.weather.interactor.WeatherUseCase
import kioli.myalgia.element.weather.mapper.WeatherMapper

internal class WeatherPresenter(private val invoker: Invoker,
                                private val mapper: WeatherMapper,
                                private val getWeather: UseCase<WeatherUseCase.Params, WeatherModel>,
                                private val locationManager: LocationManager)
    : BasePresenter<WeatherContract.View>(), WeatherContract.Presenter {

    private val locationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            locationManager.removeUpdates(this)
            val params = WeatherUseCase.Params(true, location.latitude, location.longitude)
            invoker.execute(getWeather, params, ::onWeatherArrived)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) = Unit

        override fun onProviderEnabled(provider: String) = Unit

        override fun onProviderDisabled(provider: String) = Unit
    }

    @SuppressLint("MissingPermission")
    override fun getWeather(forceNewWeather: Boolean) {
        view?.showLoading()
        if (forceNewWeather) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
            return
        }
        val params = WeatherUseCase.Params(false, 0.0, 0.0)
        invoker.execute(getWeather, params, ::onWeatherArrived)
    }

    private fun onWeatherArrived(result: Either<MyError, WeatherModel>) {
        view?.hideLoading()
        result.fold(ifLeft = {
            view?.showError(it)
        }, ifRight = {
            view?.showWeather(mapper.mapToPresentation(it))
        })
    }
}