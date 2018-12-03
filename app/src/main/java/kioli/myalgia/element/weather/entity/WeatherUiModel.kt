package kioli.myalgia.element.weather.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class WeatherUiModel(val humidity: Int = 0,
                                   val icon: String = "",
                                   val location: String = "",
                                   val precipitation: Double = 0.0,
                                   val pressure: Double = 0.0,
                                   val temperature: Double = 0.0,
                                   val temperatureFeelsLike: Double = 0.0,
                                   val windSpeed: Double = 0.0,
                                   val windDirection: String = "") : Parcelable