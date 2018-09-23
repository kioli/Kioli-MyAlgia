package kioli.myalgia.section.weather.di

import android.content.Context
import android.location.LocationManager
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.section.weather.entity.WeatherModel
import kioli.myalgia.section.weather.interactor.WeatherUseCase
import kioli.myalgia.section.weather.mvp.WeatherContract
import kioli.myalgia.section.weather.mvp.WeatherPresenter
import kioli.myalgia.section.weather.repository.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun weatherActivityModule() = Kodein.Module {
    bind<WeatherContract.Presenter>() with provider { WeatherPresenter(instance(), instance(), instance()) }
    bind<WeatherRepository>() with provider { WeatherRepository(instance(), instance()) }
    bind<UseCase<WeatherUseCase.Params, WeatherModel>>() with provider { WeatherUseCase(instance()) }

    bind<LocationManager>() with singleton {
        val context = instance() as Context
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
    bind<NetworkDataSource>() with singleton { WeatherNetworkDataSource(instance()) }
    bind<LocalDataSource>() with singleton { WeatherLocalDataSource(instance()) }
}