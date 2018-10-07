package kioli.myalgia.section.settings.entity

interface Option { val title: String }

sealed class OptionTemperature : Option {
    object Celsius : OptionTemperature() { override val title = "Celsius" }
    object Fahrenheit : OptionTemperature() { override val title = "Fahrenheit" }
}

sealed class OptionWindSpeed : Option {
    object KpH : OptionWindSpeed() { override val title = "KpH" }
    object MpH : OptionWindSpeed() { override val title = "MpH" }
}

sealed class OptionPressure : Option {
    object Millibar : OptionPressure() { override val title = "Millibar" }
    object InchesOfMercury : OptionPressure() { override val title = "InHg" }
}

sealed class OptionPrecipitation : Option {
    object Millimeters : OptionPrecipitation() { override val title = "Millimeters" }
    object Inches : OptionPrecipitation() { override val title = "Inches" }
}
