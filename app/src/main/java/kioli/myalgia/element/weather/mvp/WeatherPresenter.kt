package kioli.myalgia.element.weather.mvp

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.Invoker
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.common.mvp.BasePresenter
import kioli.myalgia.element.weather.entity.WeatherDbModel
import kioli.myalgia.element.weather.interactor.WeatherUseCase
import kioli.myalgia.element.weather.mapper.WeatherDbUiMapper
import kioli.myalgia.section.main.state.manager.IStateManager


internal class WeatherPresenter(private val invoker: Invoker,
                                private val mapper: WeatherDbUiMapper,
                                private val getWeather: UseCase<WeatherUseCase.Params, WeatherDbModel>,
                                private val fusedLocationClient: FusedLocationProviderClient,
                                private val stateManager: IStateManager)
    : BasePresenter<WeatherContract.View>(), WeatherContract.Presenter {

    private val locationRequest by lazy {
        LocationRequest().apply {
            interval = 1000
            fastestInterval = 50
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                fusedLocationClient.removeLocationUpdates(this)
                val params = WeatherUseCase.Params(location.latitude, location.longitude)
                invoker.execute(getWeather, params, ::onWeatherArrived)
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun getWeather(forceNewWeather: Boolean) {
        view?.showLoading()
        if (forceNewWeather) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            return
        }
        val params = WeatherUseCase.Params(0.0, 0.0)
        invoker.execute(getWeather, params, ::onWeatherArrived)
    }

    private fun onWeatherArrived(result: Either<MyError, WeatherDbModel>) {
        view?.hideLoading()
        result.fold(ifLeft = {
            view?.showError(it)
        }, ifRight = {
            stateManager.setWeather(it)
            view?.showWeather(mapper.mapToRight(it))
        })
    }
}