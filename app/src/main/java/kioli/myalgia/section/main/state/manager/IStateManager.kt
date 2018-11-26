package kioli.myalgia.section.main.state.manager

import kioli.myalgia.element.weather.entity.WeatherModel

internal interface IStateManager {
    fun setMood(mood: Int)
    fun setWeather(weatherModel: WeatherModel)
    fun storeState()
}