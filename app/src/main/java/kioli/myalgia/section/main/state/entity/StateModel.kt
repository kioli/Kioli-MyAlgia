package kioli.myalgia.section.main.state.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kioli.myalgia.element.weather.entity.WeatherDbModel

@Entity(tableName = "stateData")
internal data class StateModel(@PrimaryKey(autoGenerate = true) val id: Long = 0,
                               @SerializedName("mood") val mood: Int,
                               @Embedded val weather: WeatherDbModel)