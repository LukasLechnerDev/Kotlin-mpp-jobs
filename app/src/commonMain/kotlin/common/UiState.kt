package common

sealed class UiState<out T> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val throwable: Throwable? = null) : UiState<Nothing>()
    data class Loading(val msg: String? = null) : UiState<Nothing>()
}