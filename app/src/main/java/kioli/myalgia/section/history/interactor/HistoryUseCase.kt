package kioli.myalgia.section.history.interactor

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.section.history.entity.HistoryItemModel
import kioli.myalgia.section.history.repository.HistoryRepository

internal class HistoryUseCase(private val repository: HistoryRepository)
    : UseCase<Unit, List<HistoryItemModel>>() {

    override fun run(params: Unit): Either<MyError, List<HistoryItemModel>> = repository.getWeather()
}
