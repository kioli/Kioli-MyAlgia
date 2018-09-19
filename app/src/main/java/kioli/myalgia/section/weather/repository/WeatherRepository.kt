package kioli.myalgia.section.weather.repository

import android.util.Log
import kioli.myalgia.common.error.Error
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.right
import kioli.myalgia.common.repository.CachePolicy
import kioli.myalgia.common.repository.CachePolicy.*
import kioli.myalgia.section.weather.entity.WeatherModel

internal class WeatherRepository(
        private val localDataSource: LocalDataSource,
        private val networkDataSource: NetworkDataSource) {

    fun getWeather(policy: CachePolicy): Either<Error, WeatherModel> {
        return when (policy) {
            NetworkFirst -> networkDataSource.getWeather().fold(
                    {
                        Log.w("MyAlgia", "error loading weather from network: $it")
                        localDataSource.getWeather()
                    },
                    {
                        localDataSource.saveWeather(it)
                        it.right()
                    })
            LocalFirst -> localDataSource.getWeather().fold(
                    {
                        Log.w("MyAlgia", "error loading weather from local cache: $it")
                        networkDataSource.getWeather().map {
                            localDataSource.saveWeather(it)
                            it
                        }
                    },
                    { it.right() })
            LocalOnly -> localDataSource.getWeather()
            NetworkOnly -> networkDataSource.getWeather().map {
                localDataSource.saveWeather(it)
                it
            }
        }
    }
}