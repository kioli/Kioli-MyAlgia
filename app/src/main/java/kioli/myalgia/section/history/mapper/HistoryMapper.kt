package kioli.myalgia.section.history.mapper

import kioli.myalgia.common.mapper.Mapper
import kioli.myalgia.section.history.entity.HistoryItemModel
import kioli.myalgia.section.history.entity.HistoryItemUiModel
import kioli.myalgia.section.settings.entity.Option
import kioli.myalgia.section.settings.entity.OptionTemperature

/**
 * Map [HistoryItemModel] to and from a [HistoryItemUiModel]
 */
internal class HistoryMapper(private val globalSettingState: List<Option>)
    : Mapper<HistoryItemUiModel, HistoryItemModel> {

    /**
     * Map a [HistoryItemUiModel] instance to a [HistoryItemModel] instance
     * @param type weather model coming from the PRESENTATION layer
     * @return weather model proper of the DOMAIN layer
     */
    override fun mapToStored(type: HistoryItemUiModel): HistoryItemModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Map a [HistoryItemModel] instance to a [HistoryItemUiModel] instance
     * @param type weather model coming from the DOMAIN layer
     * @return weather model proper of the PRESENTATION layer
     */
    override fun mapToPresentation(type: HistoryItemModel): HistoryItemUiModel {
        var result = HistoryItemUiModel(type.mood, type.location.name)
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
