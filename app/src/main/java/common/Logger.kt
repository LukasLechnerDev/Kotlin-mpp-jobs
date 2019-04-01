package common

import android.util.Log

actual fun logDebug(tag: String, message: String) {
    Log.d(tag, message)
}

actual fun logException(tag: String, message: String?, exception: Throwable?) {
    Log.e(tag, message, exception)
}