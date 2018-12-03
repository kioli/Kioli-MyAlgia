package kioli.myalgia.element.weather.mapper

import kioli.myalgia.common.mapper.Mapper
import kioli.myalgia.element.weather.entity.WeatherApiModel
import kioli.myalgia.element.weather.entity.WeatherDbModel

/**
 * Map [WeatherApiModel] to and from a [WeatherDbModel]
 */
internal class WeatherApiDbMapper : Mapper<WeatherDbModel, WeatherApiModel> {

    /**
     * Map a [WeatherDbModel] instance to a [WeatherApiModel] instance
     * @param type weather model coming from the DB layer
     * @return weather model proper of the API layer
     */
    override fun mapToRight(type: WeatherDbModel): WeatherApiModel {
        TODO("not necessary")
    }

    /**
     * Map a [WeatherApiModel] instance to a [WeatherDbModel] instance
     * @param type weather model coming from the API layer
     * @return weather model proper of the DB layer
     */
    override fun mapToLeft(type: WeatherApiModel): WeatherDbModel {
        return WeatherDbModel(location = type.location.name,
                region = type.location.region,
                country = type.location.country,
                lat = type.location.lat,
                lon = type.location.lon,
                localtime = type.location.localtime,
                condition = type.current.condition.text,
                condition_icon = type.current.condition.icon,
                humidity = type.current.humidity,
                cloud = type.current.cloud,
                temperature_c = type.current.temperature_c,
                temperature_f = type.current.temperature_f,
                temp_feels_like_c = type.current.temp_feels_like_c,
                temp_feels_like_f = type.current.temp_feels_like_f,
                wind_kph = type.current.wind_kph,
                wind_mph = type.current.wind_mph,
                wind_degree = type.current.wind_degree,
                wind_direction = type.current.wind_direction,
                pressure_in = type.current.pressure_in,
                pressure_mb = type.current.pressure_mb,
                precipitation_in = type.current.precipitation_in,
                precipitation_mm = type.current.precipitation_mm)
    }
}
