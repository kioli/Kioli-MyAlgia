package kioli.myalgia.section.settings.entity

enum class Preference(val title: String, val option1: String, val option2: String) {
    Temperature("Temperature", "Celsius", "Fahrenheit"),
    Wind("Wind speed", "KpH", "MpH"),
    Pressure("Pressure", "Millibar", "Inches"),
    Precipitation("Precipitation", "Millimeters", "Inches")
}