package kioli.myalgia.section.settings

import android.os.Bundle
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedActivity
import kioli.myalgia.section.settings.entity.Setting
import kioli.myalgia.section.settings.entity.settingsList
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : InjectedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        settingsList.forEach { addSetting(it) }
    }

    private fun addSetting(setting: Setting) {
        preferencesContainer.addView(SettingView(baseContext, setting))
    }
}