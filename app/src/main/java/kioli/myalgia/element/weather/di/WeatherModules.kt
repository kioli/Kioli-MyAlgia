package kioli.myalgia.element.weather.di

import android.arch.persistence.room.Room
import android.content.Context
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.element.weather.db.StateDb
import kioli.myalgia.element.weather.entity.WeatherDbModel
import kioli.myalgia.element.weather.interactor.WeatherUseCase
import kioli.myalgia.element.weather.mapper.WeatherApiDbMapper
import kioli.myalgia.element.weather.mapper.WeatherDbUiMapper
import kioli.myalgia.element.weather.mvp.WeatherContract
import kioli.myalgia.element.weather.mvp.WeatherPresenter
import kioli.myalgia.element.weather.repository.SourceWeatherNetwork
import kioli.myalgia.element.weather.repository.WeatherNetworkDataSource
import kioli.myalgia.element.weather.repository.WeatherRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun weatherElementModule(context: Context) = Kodein.Module("module weather", false) {
    bind<WeatherContract.Presenter>() with provider { WeatherPresenter(instance(), instance(), instance(), instance(), instance()) }
    bind<WeatherRepository>() with provider { WeatherRepository(instance(), instance()) }
    bind<UseCase<WeatherUseCase.Params, WeatherDbModel>>() with provider { WeatherUseCase(instance()) }
    bind<FusedLocationProviderClient>() with provider { FusedLocationProviderClient(context) }

    bind<WeatherApiDbMapper>() with singleton { WeatherApiDbMapper() }
    bind<WeatherDbUiMapper>() with singleton { WeatherDbUiMapper(instance()) }
    bind<SourceWeatherNetwork>() with singleton { WeatherNetworkDataSource(instance()) }
    bind<StateDb>() with singleton { Room.databaseBuilder(instance(), StateDb::class.java, "state.db").build() }
}