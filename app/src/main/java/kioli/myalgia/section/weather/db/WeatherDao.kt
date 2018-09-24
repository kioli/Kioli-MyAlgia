package kioli.myalgia.section.weather.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import kioli.myalgia.section.weather.entity.WeatherModel

@Dao
internal interface WeatherDao {

    @Query("SELECT * from weatherData")
    fun getAll(): List<WeatherModel>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: WeatherModel)

    @Query("DELETE from weatherData")
    fun deleteAll()
}
