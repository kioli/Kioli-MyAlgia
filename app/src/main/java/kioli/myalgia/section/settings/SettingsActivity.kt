package kioli.myalgia.section.settings

import android.os.Bundle
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedActivity
import kioli.myalgia.section.settings.entity.Setting
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : InjectedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        Setting.values().forEach { addSetting(it) }
    }

    private fun addSetting(setting: Setting) {
        preferencesContainer.addView(SettingView(baseContext, setting))
    }
}