package common

expect fun logDebug(tag: String, message: String)
expect fun logException(tag: String, message: String? = null, exception: Throwable? = null)