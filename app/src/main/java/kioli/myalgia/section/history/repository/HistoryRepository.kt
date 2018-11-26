package kioli.myalgia.section.history.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.section.main.state.entity.StateModel
import timber.log.Timber

internal class HistoryRepository(private val localDataSource: SourceStateLocal) {

    fun getStateItems(): Either<MyError, List<StateModel>> {
        return localDataSource.getStateItems().fold(
                {
                    Timber.e("No history item found in local cache: $it")
                    it.left()
                },
                { it.right() }
        )
    }

    fun storeState(item: StateModel): Either<MyError, StateModel> {
        return try {
            localDataSource.saveState(item)
            item.right()
        } catch (e: Exception) {
            HistoryErrorSaving().left()
        }
    }
}