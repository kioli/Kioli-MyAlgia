package kioli.myalgia.section.history.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.section.history.entity.HistoryItemModel

/**
 * The data source contracts are part of the domain layer.
 * Their implementations belong to the data layer.
 */
internal interface SourceHistoryLocal {

    fun getHistoryItems(): Either<MyError, List<HistoryItemModel>>

    fun saveHistoryItem(historyItemModel: HistoryItemModel)
}
