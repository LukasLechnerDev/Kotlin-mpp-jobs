package common

actual fun logDebug(tag: String, message: String) {
    print("${tag} : ${message}")
}

actual fun logException(tag: String, message: String?, exception: Throwable?) {
    println("${tag} : ${message}")
    if (exception != null) {
        println(exception)
    }
}