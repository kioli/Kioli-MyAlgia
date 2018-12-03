package kioli.myalgia.section.main.state.manager

import kioli.myalgia.common.interactor.Invoker
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.element.weather.entity.WeatherDbModel
import kioli.myalgia.section.main.state.entity.StateModel
import kioli.myalgia.section.main.state.interactor.StoreStateUseCase

internal class StateManager(private val invoker: Invoker,
                            private val storeState: UseCase<StoreStateUseCase.Params, StateModel>)
    : IStateManager {

    private var status = StateModel(
            mood = 0,
            weather = WeatherDbModel("", "", "", 0.0, 0.0, "",
                    "", "", 0, 0, 0.0, 0.0,
                    0.0, 0.0, 0.0, 0.0, 0,
                    "", 0.0, 0.0, 0.0, 0.0)
    )

    override fun setMood(mood: Int) {
        status = status.copy(mood = mood)
    }

    override fun setWeather(weatherModel: WeatherDbModel) {
        status = status.copy(weather = weatherModel)
    }

    override fun storeState() {
        val params = StoreStateUseCase.Params(status)
        invoker.execute(storeState, params) {}
    }
}