package kioli.myalgia.element.weather.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kioli.myalgia.section.main.state.db.StateDao
import kioli.myalgia.section.main.state.entity.StateModel

@Database(entities = [StateModel::class], version = 1)
internal abstract class StateDb : RoomDatabase() {
    abstract fun stateDao(): StateDao
}
