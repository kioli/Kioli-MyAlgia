package kioli.myalgia.element.weather.entity

import com.google.gson.annotations.SerializedName

internal data class WeatherDbModel(val location: String,
                                   val region: String,
                                   val country: String,
                                   val lat: Double,
                                   val lon: Double,
                                   val localtime: String,
                                   val condition: String,
                                   val condition_icon: String,
                                   val humidity: Int,
                                   val cloud: Int,
                                   @SerializedName("temp_c") val temperature_c: Double,
                                   @SerializedName("temp_f") val temperature_f: Double,
                                   @SerializedName("feels_like_c") val temp_feels_like_c: Double,
                                   @SerializedName("feels_like_f") val temp_feels_like_f: Double,
                                   val wind_kph: Double,
                                   val wind_mph: Double,
                                   val wind_degree: Int,
                                   @SerializedName("wind_dir") val wind_direction: String,
                                   val pressure_in: Double,
                                   val pressure_mb: Double,
                                   @SerializedName("precipitation_in") val precipitation_in: Double,
                                   @SerializedName("precipitation_mm") val precipitation_mm: Double)
