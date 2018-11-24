package kioli.myalgia.section.history.repository

import kioli.myalgia.common.error.MyError
import kioli.myalgia.common.functional.Either
import kioli.myalgia.common.functional.left
import kioli.myalgia.common.functional.right
import kioli.myalgia.section.history.entity.HistoryItemModel
import timber.log.Timber

internal class HistoryRepository(private val localDataSource: SourceHistoryLocal) {

    fun getWeather(): Either<MyError, List<HistoryItemModel>> {
        return localDataSource.getHistoryItems().fold(
                {
                    Timber.e("No history item found in local cache: $it")
                    it.left()
                },
                { it.right() }
        )
    }
}