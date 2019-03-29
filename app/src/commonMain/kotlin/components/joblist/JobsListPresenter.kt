package components.joblist

import api.IoDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import presentation.BasePresenter
import presentation.JobsListView
import repository.JobPositionRepository
import kotlin.coroutines.CoroutineContext

class JobsListPresenter(
    private val view: JobsListView,
    private val repository: JobPositionRepository
) : BasePresenter(view) {

    fun getJobsList() {
        view.showProgressIndicator(true)

        launch(IoDispatcher) {
            val data = repository.getJobsList()
            withContext(Dispatchers.Main){
                view.getJobsListSuccess(data)
            }
        }.invokeOnCompletion {
            view.showProgressIndicator(false)
        }
    }
}