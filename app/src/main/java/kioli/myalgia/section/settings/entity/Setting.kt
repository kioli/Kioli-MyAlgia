package kioli.myalgia.section.settings.entity

import kioli.myalgia.section.settings.entity.OptionPrecipitation.Inches
import kioli.myalgia.section.settings.entity.OptionPrecipitation.Millimeters
import kioli.myalgia.section.settings.entity.OptionPressure.InchesOfMercury
import kioli.myalgia.section.settings.entity.OptionPressure.Millibar
import kioli.myalgia.section.settings.entity.OptionTemperature.Celsius
import kioli.myalgia.section.settings.entity.OptionTemperature.Fahrenheit
import kioli.myalgia.section.settings.entity.OptionWindSpeed.KpH
import kioli.myalgia.section.settings.entity.OptionWindSpeed.MpH

val settingsList = arrayListOf(
        Setting.SettingTemperature(),
        Setting.SettingWindSpeed(),
        Setting.SettingPressure(),
        Setting.SettingPrecipitation())

sealed class Setting {
    abstract val title: String
    abstract val options: List<Option>

    data class SettingTemperature(override val title: String = "Temperature",
                                  override val options: List<OptionTemperature> = listOf(Celsius, Fahrenheit)) : Setting()

    data class SettingWindSpeed(override val title: String = "Wind speed",
                                override val options: List<OptionWindSpeed> = listOf(KpH, MpH)) : Setting()

    data class SettingPressure(override val title: String = "Pressure",
                               override val options: List<OptionPressure> = listOf(InchesOfMercury, Millibar)) : Setting()

    data class SettingPrecipitation(override val title: String = "Precipitation",
                                    override val options: List<OptionPrecipitation> = listOf(Inches, Millimeters)) : Setting()
}