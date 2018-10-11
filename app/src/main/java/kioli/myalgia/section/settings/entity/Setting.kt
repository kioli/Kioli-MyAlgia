package kioli.myalgia.section.settings.entity

import kioli.myalgia.section.settings.entity.OptionPrecipitation.Inches
import kioli.myalgia.section.settings.entity.OptionPrecipitation.Millimeters
import kioli.myalgia.section.settings.entity.OptionPressure.InchesOfMercury
import kioli.myalgia.section.settings.entity.OptionPressure.Millibar
import kioli.myalgia.section.settings.entity.OptionTemperature.Celsius
import kioli.myalgia.section.settings.entity.OptionTemperature.Fahrenheit
import kioli.myalgia.section.settings.entity.OptionWindSpeed.KpH
import kioli.myalgia.section.settings.entity.OptionWindSpeed.MpH

enum class Setting(val title: String, val options: List<Option>) {
    SettingTemperature("Temperature", listOf(Celsius, Fahrenheit)),
    SettingWindSpeed("Wind speed", listOf(KpH, MpH)),
    SettingPressure("Pressure", listOf(InchesOfMercury, Millibar)),
    SettingPrecipitation("Precipitation", listOf(Inches, Millimeters))
}
