package kioli.myalgia.section.history.di

import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.element.weather.mvp.HistoryContract
import kioli.myalgia.section.main.state.entity.StateModel
import kioli.myalgia.section.history.interactor.HistoryLoadUseCase
import kioli.myalgia.section.history.mapper.HistoryMapper
import kioli.myalgia.section.history.mvp.HistoryPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun historyViewModules() = Kodein.Module("module history", false) {
    bind<HistoryContract.Presenter>() with provider { HistoryPresenter(instance(), instance(), instance()) }
    bind<UseCase<Unit, List<StateModel>>>() with provider { HistoryLoadUseCase(instance()) }
    bind<HistoryMapper>() with singleton { HistoryMapper(instance()) }
}
