package kioli.myalgia.element.weather.di

import android.arch.persistence.room.Room
import android.content.Context
import android.location.LocationManager
import kioli.myalgia.common.ext.readSettingOptionFromSharedPref
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.element.weather.db.WeatherDb
import kioli.myalgia.element.weather.entity.WeatherModel
import kioli.myalgia.element.weather.interactor.WeatherUseCase
import kioli.myalgia.element.weather.mapper.WeatherMapper
import kioli.myalgia.element.weather.mvp.WeatherContract
import kioli.myalgia.element.weather.mvp.WeatherPresenter
import kioli.myalgia.element.weather.repository.*
import kioli.myalgia.section.settings.entity.Option
import kioli.myalgia.section.settings.entity.Setting
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun weatherActivityModule() = Kodein.Module {
    bind<WeatherContract.Presenter>() with provider { WeatherPresenter(instance(), instance(), instance(), instance()) }
    bind<WeatherRepository>() with provider { WeatherRepository(instance(), instance()) }
    bind<UseCase<WeatherUseCase.Params, WeatherModel>>() with provider { WeatherUseCase(instance()) }

    bind<LocationManager>() with singleton {
        val context = instance() as Context
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
    bind<List<Option>>() with provider {
        val context = instance() as Context
        Setting.values().map { context.readSettingOptionFromSharedPref(it) }
    }
    bind<WeatherMapper>() with singleton { WeatherMapper(instance()) }
    bind<NetworkDataSource>() with singleton { WeatherNetworkDataSource(instance()) }
    bind<LocalDataSource>() with singleton { WeatherLocalDataSource(instance()) }
    bind<WeatherDb>() with singleton { Room.databaseBuilder(instance(), WeatherDb::class.java, "weather.db").build() }
}