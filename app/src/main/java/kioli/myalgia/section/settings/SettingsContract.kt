package kioli.myalgia.section.settings

import kioli.myalgia.common.mvp.PresenterI
import kioli.myalgia.common.mvp.ViewI

internal interface SettingsContract {
    interface View : ViewI {
    }

    interface Presenter : PresenterI<View> {
    }
}