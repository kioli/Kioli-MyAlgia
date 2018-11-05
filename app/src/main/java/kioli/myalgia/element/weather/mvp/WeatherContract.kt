package kioli.myalgia.element.weather.mvp

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.mvp.PresenterI
import kioli.myalgia.common.mvp.ViewI
import kioli.myalgia.element.weather.entity.WeatherUiModel

internal interface WeatherContract {

    interface View : ViewI {
        /**
         * Show the weather result in the calling WeatherElement
         *
         * @param weather the requested weather
         */
        fun showWeather(weather: WeatherUiModel)

        /**
         * Show an error result in the calling WeatherElement
         *
         * @param error error occurred
         */
        fun showError(error: MyError)

        /**
         * Show the loading screen in the WeatherElement
         */
        fun showLoading()

        /**
         * Hide the loading screen in the WeatherElement
         */
        fun hideLoading()

        /**
         * Request weather after granting permissions
         */
        fun requestWeather(forceNewWeather: Boolean)
    }

    interface Presenter : PresenterI<View> {

        /**
         * Get a new weather to present to the WeatherElement
         *
         * @param forceNewWeather true if the weather to get should be a new one, false otherwise
         */
        fun getWeather(forceNewWeather: Boolean)
    }
}