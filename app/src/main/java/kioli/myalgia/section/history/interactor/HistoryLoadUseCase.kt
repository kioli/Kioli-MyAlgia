package kioli.myalgia.section.history.interactor

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.section.main.state.entity.StateModel
import kioli.myalgia.section.history.repository.HistoryRepository

internal class HistoryLoadUseCase(private val repository: HistoryRepository)
    : UseCase<Unit, List<StateModel>>() {

    override fun run(params: Unit): Either<MyError, List<StateModel>> = repository.getStateItems()
}
