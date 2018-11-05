package kioli.myalgia.element.weather.api

import kioli.myalgia.element.weather.entity.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Classic Retrofit2 interface to define network endpoints
 */
internal interface WeatherApi {

    @GET("current.json")
    fun loadWeather(@Query("key") key: String,
                    @Query("q") latLon: String): Call<WeatherModel>
}