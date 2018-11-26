package kioli.myalgia.section.history.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.section.main.state.db.StateDb
import kioli.myalgia.section.main.state.entity.StateModel

internal class HistoryLocalDataSource(private val db: StateDb) : SourceStateLocal {

    override fun getStateItems(): Either<MyError, List<StateModel>> {
        if (db.stateDao().getAll().isNotEmpty()) {
            return db.stateDao().getAll().right()
        }
        return HistoryErrorSaving().left()
    }

    override fun saveState(stateModel: StateModel) {
        db.stateDao().insert(stateModel)
    }
}
