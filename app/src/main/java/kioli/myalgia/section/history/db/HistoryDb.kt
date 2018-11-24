package kioli.myalgia.section.history.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kioli.myalgia.section.history.entity.HistoryItemModel

@Database(entities = [HistoryItemModel::class], version = 1)
internal abstract class HistoryDb : RoomDatabase() {
    abstract fun historyModelDao(): HistoryDao
}
