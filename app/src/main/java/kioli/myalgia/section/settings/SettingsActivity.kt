package kioli.myalgia.section.settings

import android.os.Bundle
import kioli.myalgia.R
import kioli.myalgia.common.di.InjectedActivity

class SettingsActivity : InjectedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
}