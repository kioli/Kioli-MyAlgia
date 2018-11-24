package kioli.myalgia.section.history.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.section.history.db.HistoryDb
import kioli.myalgia.section.history.entity.HistoryItemModel

internal class HistoryLocalDataSource(private val db: HistoryDb) : SourceHistoryLocal {

    override fun getHistoryItems(): Either<MyError, List<HistoryItemModel>> {
        if (db.historyModelDao().getAll().isNotEmpty()) {
            return db.historyModelDao().getAll().right()
        }
        return HistoryError().left()
    }

    override fun saveHistoryItem(historyItemModel: HistoryItemModel) {
        db.historyModelDao().insert(historyItemModel)
    }
}
