package kioli.myalgia.section.history.entity

internal data class HistoryItemModel(val mood: Int,
                                     val location: String = "",
                                     val temperature: Double = 0.0,
                                     val temperatureFeelsLike: Double = 0.0)