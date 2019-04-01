package common

// https://github.com/kittinunf/Result
sealed class Outcome<out V : Any, out E : Exception> {

    open operator fun component1(): V? = null
    open operator fun component2(): E? = null

    inline fun <X> fold(success: (V) -> X, failure: (E) -> X): X = when (this) {
        is Success -> success(this.value)
        is Failure -> failure(this.error)
    }

    abstract fun get(): V

    class Success<out V : Any>(val value: V) : Outcome<V, Nothing>() {
        override fun component1(): V? = value

        override fun get(): V = value

        override fun toString() = "[Success: $value]"

        override fun hashCode(): Int = value.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Success<*> && value == other.value
        }
    }

    class Failure<out E : Exception>(val error: E) : Outcome<Nothing, E>() {
        override fun component2(): E? = error

        override fun get() = throw error

        fun getException(): E = error

        override fun toString() = "[Failure: $error]"

        override fun hashCode(): Int = error.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Failure<*> && error == other.error
        }
    }

    companion object {
        // Factory methods
        fun <E : Exception> error(ex: E) = Failure(ex)

        fun <V : Any> success(v: V) = Success(v)

        fun <V : Any> of(value: V?, fail: (() -> Exception) = { Exception() }): Outcome<V, Exception> =
            value?.let { success(it) } ?: error(fail())

        fun <V : Any, E : Exception> of(f: () -> V): Outcome<V, E> = try {
            success(f())
        } catch (ex: Exception) {
            error(ex as E)
        }
    }
}
inline fun <reified X> Outcome<*, *>.getAs() = when (this) {
    is Outcome.Success -> value as? X
    is Outcome.Failure -> error as? X
}

fun <V : Any> Outcome<V, *>.success(f: (V) -> Unit) = fold(f, {})

fun <E : Exception> Outcome<*, E>.failure(f: (E) -> Unit) = fold({}, f)

infix fun <V : Any, E : Exception> Outcome<V, E>.or(fallback: V) = when (this) {
    is Outcome.Success -> this
    else -> Outcome.Success(fallback)
}

infix fun <V : Any, E : Exception> Outcome<V, E>.getOrElse(fallback: V) = when (this) {
    is Outcome.Success -> value
    else -> fallback
}

fun <V : Any, U : Any, E : Exception> Outcome<V, E>.map(transform: (V) -> U): Outcome<U, E> = try {
    when (this) {
        is Outcome.Success -> Outcome.Success(transform(value))
        is Outcome.Failure -> Outcome.Failure(error)
    }
} catch (ex: Exception) {
    Outcome.error(ex as E)
}

fun <V : Any, U : Any, E : Exception> Outcome<V, E>.flatMap(transform: (V) -> Outcome<U, E>): Outcome<U, E> = try {
    when (this) {
        is Outcome.Success -> transform(value)
        is Outcome.Failure -> Outcome.Failure(error)
    }
} catch (ex: Exception) {
    Outcome.error(ex as E)
}

fun <V : Any, E : Exception, E2 : Exception> Outcome<V, E>.mapError(transform: (E) -> E2) = when (this) {
    is Outcome.Success -> Outcome.Success(value)
    is Outcome.Failure -> Outcome.Failure(transform(error))
}

fun <V : Any, E : Exception, E2 : Exception> Outcome<V, E>.flatMapError(transform: (E) -> Outcome<V, E2>) = when (this) {
    is Outcome.Success -> Outcome.Success(value)
    is Outcome.Failure -> transform(error)
}

fun <V : Any, E : Exception> Outcome<V, E>.any(predicate: (V) -> Boolean): Boolean = try {
    when (this) {
        is Outcome.Success -> predicate(value)
        is Outcome.Failure -> false
    }
} catch (ex: Exception) {
    false
}

fun <V : Any, U : Any> Outcome<V, *>.fanout(other: () -> Outcome<U, *>): Outcome<Pair<V, U>, *> =
    flatMap { outer -> other().map { outer to it } }
