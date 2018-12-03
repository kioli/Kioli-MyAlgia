package kioli.myalgia.section.history.mapper

import kioli.myalgia.common.mapper.Mapper
import kioli.myalgia.section.history.entity.HistoryItemModel
import kioli.myalgia.section.main.state.entity.StateModel
import kioli.myalgia.section.settings.entity.Option
import kioli.myalgia.section.settings.entity.OptionTemperature

/**
 * Map [StateModel] to and from a [HistoryItemModel]
 */
internal class HistoryMapper(private val globalSettingState: List<Option>)
    : Mapper<HistoryItemModel, StateModel> {

    /**
     * Map a [HistoryItemModel] instance to a [StateModel] instance
     * @param type weather model coming from the PRESENTATION layer
     * @return weather model proper of the DOMAIN layer
     */
    override fun mapToRight(type: HistoryItemModel): StateModel {
        TODO("not necessary")
    }

    /**
     * Map a [StateModel] instance to a [HistoryItemModel] instance
     * @param type weather model coming from the DOMAIN layer
     * @return weather model proper of the PRESENTATION layer
     */
    override fun mapToLeft(type: StateModel): HistoryItemModel {
        var result = HistoryItemModel(type.mood, type.weather.location)
        globalSettingState.forEach {
            when (it) {
                is OptionTemperature -> {
                    result = when (it) {
                        OptionTemperature.Celsius -> result.copy(temperature = type.weather.temperature_c,
                                temperatureFeelsLike = type.weather.temp_feels_like_c)
                        OptionTemperature.Fahrenheit -> result.copy(temperature = type.weather.temperature_f,
                                temperatureFeelsLike = type.weather.temp_feels_like_f)
                    }
                }
            }
        }
        return result
    }
}
