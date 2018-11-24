package kioli.myalgia.element.weather.mapper

import kioli.myalgia.common.mapper.Mapper
import kioli.myalgia.element.weather.entity.WeatherModel
import kioli.myalgia.element.weather.entity.WeatherUiModel
import kioli.myalgia.section.settings.entity.*

/**
 * Map [WeatherModel] to and from a [WeatherUiModel]
 */
internal class WeatherMapper(private val globalSettingState: List<Option>)
    : Mapper<WeatherUiModel, WeatherModel> {

    /**
     * Map a [WeatherUiModel] instance to a [WeatherModel] instance
     * @param type weather model coming from the PRESENTATION layer
     * @return weather model proper of the DOMAIN layer
     */
    override fun mapToStored(type: WeatherUiModel): WeatherModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Map a [WeatherModel] instance to a [WeatherUiModel] instance
     * @param type weather model coming from the DOMAIN layer
     * @return weather model proper of the PRESENTATION layer
     */
    override fun mapToPresentation(type: WeatherModel): WeatherUiModel {
        var result = WeatherUiModel(location = type.location.name,
                humidity = type.current.humidity,
                icon = type.current.condition.icon,
                windDirection = type.current.wind_direction)
        globalSettingState.forEach {
            when (it) {
                is OptionTemperature -> {
                    result = when (it) {
                        OptionTemperature.Celsius -> result.copy(temperature = type.current.temperature_c,
                                temperatureFeelsLike = type.current.temp_feels_like_c)
                        OptionTemperature.Fahrenheit -> result.copy(temperature = type.current.temperature_f,
                                temperatureFeelsLike = type.current.temp_feels_like_f)
                    }
                }
                is OptionPrecipitation -> {
                    result = when (it) {
                        OptionPrecipitation.Inches -> result.copy(precipitation = type.current.precipitation_in)
                        OptionPrecipitation.Millimeters -> result.copy(precipitation = type.current.precipitation_mm)
                    }
                }
                is OptionPressure -> {
                    result = when (it) {
                        OptionPressure.InchesOfMercury -> result.copy(pressure = type.current.pressure_in)
                        OptionPressure.Millibar -> result.copy(pressure = type.current.pressure_mb)
                    }
                }
                is OptionWindSpeed -> {
                    result = when (it) {
                        OptionWindSpeed.KpH -> result.copy(windSpeed = type.current.wind_kph)
                        OptionWindSpeed.MpH -> result.copy(windSpeed = type.current.wind_mph)
                    }
                }
            }
        }
        return result
    }
}
