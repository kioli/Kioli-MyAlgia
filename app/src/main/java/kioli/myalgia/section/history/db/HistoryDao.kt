package kioli.myalgia.section.history.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import kioli.myalgia.section.history.entity.HistoryItemModel

@Dao
internal interface HistoryDao {

    @Query("SELECT * from historyData")
    fun getAll(): List<HistoryItemModel>

    @Insert(onConflict = REPLACE)
    fun insert(historyData: HistoryItemModel)

    @Query("DELETE from historyData")
    fun deleteAll()
}
