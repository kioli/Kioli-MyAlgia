package kioli.myalgia.section.main.state.manager

import kioli.myalgia.common.interactor.Invoker
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.element.weather.entity.Condition
import kioli.myalgia.element.weather.entity.CurrentWeather
import kioli.myalgia.element.weather.entity.Location
import kioli.myalgia.element.weather.entity.WeatherModel
import kioli.myalgia.section.main.state.interactor.StoreStateUseCase
import kioli.myalgia.section.main.state.entity.StateModel

internal class StateManager(private val invoker: Invoker,
                            private val storeState: UseCase<StoreStateUseCase.Params, StateModel>)
    : IStateManager {

    private var status = StateModel(
            mood = 0,
            location = Location("", "", "", 0.0, 0.0, 150, ""),
            weather = CurrentWeather(1, "", 0.0, 0.0, 0, Condition("", "", 0), 0.0, 0.0, 0, "", 0.0, 0.0, 0.0, 0.0, 0, 0, 0.0, 0.0))


    override fun setMood(mood: Int) {
        status = status.copy(mood = mood)
    }

    override fun setWeather(weatherModel: WeatherModel) {
        status = status.copy(weather = weatherModel.current, location = weatherModel.location)
    }

    override fun storeState() {
        val params = StoreStateUseCase.Params(status)
        invoker.execute(storeState, params) {}
    }
}