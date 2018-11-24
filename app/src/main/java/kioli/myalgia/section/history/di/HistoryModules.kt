package kioli.myalgia.section.history.di

import android.arch.persistence.room.Room
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.element.weather.mvp.HistoryContract
import kioli.myalgia.section.history.db.HistoryDb
import kioli.myalgia.section.history.entity.HistoryItemModel
import kioli.myalgia.section.history.interactor.HistoryUseCase
import kioli.myalgia.section.history.mapper.HistoryMapper
import kioli.myalgia.section.history.mvp.HistoryPresenter
import kioli.myalgia.section.history.repository.HistoryLocalDataSource
import kioli.myalgia.section.history.repository.HistoryRepository
import kioli.myalgia.section.history.repository.SourceHistoryLocal
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun historyViewModules() = Kodein.Module("module history", false) {
    bind<HistoryContract.Presenter>() with provider { HistoryPresenter(instance(), instance(), instance()) }
    bind<HistoryRepository>() with provider { HistoryRepository(instance()) }
    bind<UseCase<Unit, List<HistoryItemModel>>>() with provider { HistoryUseCase(instance()) }

    bind<HistoryMapper>() with singleton { HistoryMapper(instance()) }
    bind<SourceHistoryLocal>() with singleton { HistoryLocalDataSource(instance()) }
    bind<HistoryDb>() with singleton { Room.databaseBuilder(instance(), HistoryDb::class.java, "history.db").build() }
}
