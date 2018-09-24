package kioli.myalgia

import android.app.Application
import android.support.annotation.VisibleForTesting
import kioli.myalgia.common.di.appModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import timber.log.Timber

class App : Application(), KodeinAware {

    @VisibleForTesting
    var overrideBindings: Kodein.MainBuilder.() -> Unit = {}

    override val kodein = Kodein.lazy {
        import(appModule(applicationContext))
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            return
        }
        Timber.plant(ReleaseTree())
    }
}
