package kioli.myalgia.section.weather.mvp

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import kioli.myalgia.common.error.Error
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.Invoker
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.common.mvp.BasePresenter
import kioli.myalgia.section.weather.entity.WeatherModel
import kioli.myalgia.section.weather.interactor.WeatherUseCase
import kioli.myalgia.section.weather.mvp.WeatherContract.Presenter

internal class WeatherPresenter(private val invoker: Invoker,
                                private val getWeather: UseCase<WeatherUseCase.Params, WeatherModel>,
                                private val locationManager: LocationManager)
    : BasePresenter<WeatherContract.View>(), Presenter {

    private val locationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            locationManager.removeUpdates(this)
            val params = WeatherUseCase.Params(true, location.latitude, location.longitude)
            invoker.execute(getWeather, params, ::onWeatherArrived)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }

    @SuppressLint("MissingPermission")
    override fun getWeather(forceNew: Boolean) {
        view?.showLoading()
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener)
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