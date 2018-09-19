package kioli.myalgia.section.weather.entity

import com.google.gson.annotations.SerializedName

internal data class WeatherModel(@SerializedName("current") val current: CurrentWeather)

internal data class CurrentWeather(@SerializedName("temp_c") val temperature: Float,
                                   @SerializedName("pressure_mb") val pressure: Float,
                                   @SerializedName("humidity") val humidity: Float)