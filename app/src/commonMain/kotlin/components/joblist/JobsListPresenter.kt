package components.joblist

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import presentation.BasePresenter
import presentation.JobsListView
import repository.JobPositionRepository
import kotlin.coroutines.CoroutineContext

class JobsListPresenter(
    private val uiContext: CoroutineContext,
    private val view: JobsListView,
    private val repository: JobPositionRepository
) : BasePresenter(uiContext, view) {

    fun getJobsList() {
        view.showProgressIndicator(true)

        launch(coroutineContext) {
            val data = repository.getJobsList()
            withContext(uiContext) {
                view.getJobsListSuccess(data)
            }
        }.invokeOnCompletion {
            view.showProgressIndicator(false)
        }

    }

}