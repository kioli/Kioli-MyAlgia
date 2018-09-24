package kioli.myalgia.section.weather.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kioli.myalgia.section.weather.entity.WeatherModel

@Database(entities = [WeatherModel::class], version = 1)
internal abstract class WeatherDb : RoomDatabase() {
    abstract fun weatherModelDao(): WeatherDao
}
