package kioli.myalgia.section.main.state.manager

import kioli.myalgia.element.weather.entity.WeatherDbModel

internal interface IStateManager {
    fun setMood(mood: Int)
    fun setWeather(weatherModel: WeatherDbModel)
    fun storeState()
}