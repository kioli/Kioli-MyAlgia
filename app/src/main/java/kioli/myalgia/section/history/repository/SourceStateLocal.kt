package kioli.myalgia.section.history.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.section.main.state.entity.StateModel

/**
 * The data source contracts are part of the domain layer.
 * Their implementations belong to the data layer.
 */
internal interface SourceStateLocal {

    fun getStateItems(): Either<MyError, List<StateModel>>

    fun saveState(stateModel: StateModel)
}
