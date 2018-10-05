package kioli.myalgia.section.settings

import android.os.Bundle
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedActivity
import kioli.myalgia.section.settings.entity.Preference
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : InjectedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        addSettings()
    }

    private fun addSettings() {
        Preference.values().forEach { preferencesContainer.addView(SettingView(baseContext, it)) }
    }
}