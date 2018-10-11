package kioli.myalgia.section.settings.entity

interface Option {
    val title: String
}

enum class OptionTemperature(override val title: String) : Option {
    Celsius("Celsius"), Fahrenheit("Fahrenheit")
}

enum class OptionWindSpeed(override val title: String) : Option {
    KpH("KpH"), MpH("MpH")
}

enum class OptionPressure(override val title: String) : Option {
    InchesOfMercury("InchesOfMercury"), Millibar("Millibar")
}

enum class OptionPrecipitation(override val title: String) : Option {
    Inches("Inches"), Millimeters("Millimeters")
}
