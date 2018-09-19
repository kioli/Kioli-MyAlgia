package kioli.myalgia.section.weather.mvp

import kioli.myalgia.common.mvp.PresenterI
import kioli.myalgia.common.mvp.ViewI
import kioli.myalgia.section.weather.entity.WeatherModel

internal interface WeatherContract {

    interface View : ViewI {
        /**
         * Return the result of asking for a weather to the calling WeatherView
         *
         * @param weather the requested weather
         */
        fun returnResultWeather(weather: WeatherModel?)

        /**
         * Show the loading screen in the WeatherView
         */
        fun showLoading()

        /**
         * Hide the loading screen in the WeatherView
         */
        fun hideLoading()
    }

    interface Presenter : PresenterI<View> {

        /**
         * Get a new weather to present to the WeatherView
         *
         * @param forceNew true if the weather to get should be a new one, false otherwise
         */
        fun getWeather(forceNew: Boolean)
    }
}