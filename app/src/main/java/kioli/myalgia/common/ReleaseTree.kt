package kioli.myalgia.common

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

internal class ReleaseTree : Timber.Tree() {

    private val maxLogLength = 4000

    override fun isLoggable(tag: String?, priority: Int): Boolean = when (priority) {
        Log.VERBOSE, Log.DEBUG, Log.INFO -> false
        else -> true
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (isLoggable(tag, priority)) {
            if (priority == Log.ERROR && t != null) {
                Crashlytics.logException(t)
            }

            message.chunked(maxLogLength).forEach { it ->
                when (priority) {
                    Log.ASSERT -> Timber.tag(tag).wtf(it)
                    else -> Log.println(priority, tag, it)
                }
            }
        }
    }
}