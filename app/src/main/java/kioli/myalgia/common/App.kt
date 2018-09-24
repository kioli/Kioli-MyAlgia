package kioli.myalgia.common

import android.app.Application
import android.support.annotation.VisibleForTesting
import kioli.myalgia.common.di.appModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import timber.log.Timber
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import kioli.myalgia.BuildConfig

class App : Application(), KodeinAware {

    @VisibleForTesting
    var overrideBindings: Kodein.MainBuilder.() -> Unit = {}

    override val kodein = Kodein.lazy {
        import(appModule(applicationContext))
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ':' + element.lineNumber
                }
            })
            return
        }
        Fabric.with(this, Crashlytics())
        Timber.plant(ReleaseTree())
    }
}
