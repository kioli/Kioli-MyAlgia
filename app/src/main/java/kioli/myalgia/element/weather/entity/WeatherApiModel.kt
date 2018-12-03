package kioli.myalgia.element.weather.entity

import com.google.gson.annotations.SerializedName

internal data class WeatherApiModel(val location: Location,
                                    val current: CurrentWeather)

internal data class Location(val name: String,
                             val region: String,
                             val country: String,
                             val lat: Double,
                             val lon: Double,
                             val localtime_epoch: Int,
                             val localtime: String)

internal data class CurrentWeather(val last_updated_epoch: Int,
                                   val last_updated: String,
                                   @SerializedName("temp_c") val temperature_c: Double,
                                   @SerializedName("temp_f") val temperature_f: Double,
                                   val is_day: Int,
                                   val condition: Condition,
                                   val wind_mph: Double,
                                   val wind_kph: Double,
                                   val wind_degree: Int,
                                   @SerializedName("wind_dir") val wind_direction: String,
                                   val pressure_mb: Double,
                                   val pressure_in: Double,
                                   @SerializedName("precip_mm") val precipitation_mm: Double,
                                   @SerializedName("precip_in") val precipitation_in: Double,
                                   val humidity: Int,
                                   val cloud: Int,
                                   @SerializedName("feelslike_c") val temp_feels_like_c: Double,
                                   @SerializedName("feelslike_f") val temp_feels_like_f: Double)

internal data class Condition(val text: String,
                              val icon: String,
                              val code: Int)