package kioli.myalgia

import android.util.Log
import timber.log.Timber

internal class ReleaseTree : Timber.Tree() {

    private val maxLogLength = 4000

    override fun isLoggable(tag: String?, priority: Int): Boolean = when (priority) {
        Log.VERBOSE, Log.DEBUG, Log.INFO -> false
        else -> true
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (isLoggable(tag, priority)) {
            if (message.length < maxLogLength) {
                printMessage(priority, tag, message)
                return
            }
            message.chunked(maxLogLength).forEach {
                printMessage(priority, tag, it)
            }
        }
    }

    private fun printMessage(priority: Int, tag: String?, message: String) {
        when (priority) {
            Log.ASSERT -> Timber.tag(tag).wtf(message)
            else -> Log.println(priority, tag, message)
        }
    }
}