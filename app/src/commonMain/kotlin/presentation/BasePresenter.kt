package presentation

import api.IoDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import presentation.BaseView
import kotlin.coroutines.CoroutineContext

open class BasePresenter (
    private val baseView: BaseView
): CoroutineScope {

    private val job = Job()

    /*private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        baseView.showError(throwable)
    }*/

    override val coroutineContext: CoroutineContext
        get() = IoDispatcher + job // + exceptionHandler

    open fun onDestroy() {
        job.cancel()
    }
}