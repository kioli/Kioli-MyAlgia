package kioli.myalgia.section.main.state.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import kioli.myalgia.section.main.state.entity.StateModel

@Dao
internal interface StateDao {

    @Query("SELECT * from stateData")
    fun getAll(): List<StateModel>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(state: StateModel)

    @Query("DELETE from stateData")
    fun deleteAll()
}
