package kioli.myalgia.element.weather.mapper

import kioli.myalgia.common.mapper.Mapper
import kioli.myalgia.element.weather.entity.WeatherDbModel
import kioli.myalgia.element.weather.entity.WeatherUiModel
import kioli.myalgia.section.settings.entity.*

/**
 * Map [WeatherDbModel] to and from a [WeatherUiModel]
 */
internal class WeatherDbUiMapper(private val globalSettingState: List<Option>)
    : Mapper<WeatherDbModel, WeatherUiModel> {

    /**
     * Map a [WeatherDbModel] instance to a [WeatherUiModel] instance
     * @param type weather model coming from the API layer
     * @return weather model proper of the UI layer
     */
    override fun mapToRight(type: WeatherDbModel): WeatherUiModel {
        var result = WeatherUiModel(
                humidity = type.humidity,
                icon = type.condition_icon,
                location = "${type.location}, ${type.region}, ${type.country}",
                windDirection = type.wind_direction)
        globalSettingState.forEach {
            when (it) {
                is OptionTemperature -> {
                    result = when (it) {
                        OptionTemperature.Celsius -> result.copy(temperature = type.temperature_c,
                                temperatureFeelsLike = type.temp_feels_like_c)
                        OptionTemperature.Fahrenheit -> result.copy(temperature = type.temperature_f,
                                temperatureFeelsLike = type.temp_feels_like_f)
                    }
                }
                is OptionPrecipitation -> {
                    result = when (it) {
                        OptionPrecipitation.Inches -> result.copy(precipitation = type.precipitation_in)
                        OptionPrecipitation.Millimeters -> result.copy(precipitation = type.precipitation_mm)
                    }
                }
                is OptionPressure -> {
                    result = when (it) {
                        OptionPressure.InchesOfMercury -> result.copy(pressure = type.pressure_in)
                        OptionPressure.Millibar -> result.copy(pressure = type.pressure_mb)
                    }
                }
                is OptionWindSpeed -> {
                    result = when (it) {
                        OptionWindSpeed.KpH -> result.copy(windSpeed = type.wind_kph)
                        OptionWindSpeed.MpH -> result.copy(windSpeed = type.wind_mph)
                    }
                }
            }
        }
        return result
    }

    /**
     * Map a [WeatherUiModel] instance to a [WeatherDbModel] instance
     * @param type weather model coming from the UI layer
     * @return weather model proper of the API layer
     */
    override fun mapToLeft(type: WeatherUiModel): WeatherDbModel {
        TODO("not necessary")
    }
}
