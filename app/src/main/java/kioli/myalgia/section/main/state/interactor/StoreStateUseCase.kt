package kioli.myalgia.section.main.state.interactor

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.interactor.UseCase
import kioli.myalgia.section.main.state.entity.StateModel
import kioli.myalgia.section.history.repository.HistoryRepository

internal class StoreStateUseCase(private val repository: HistoryRepository)
    : UseCase<StoreStateUseCase.Params, StateModel>() {

    override fun run(params: Params): Either<MyError, StateModel> = repository.storeState(params.item)

    data class Params(val item: StateModel)
}
