package components.joblist

import api.GithubJobsApi
import api.IoDispatcher
import kotlinx.coroutines.launch
import presentation.BasePresenter
import presentation.JobsListView
import repository.*

class JobsListPresenter(
    private val view: JobsListView
) : BasePresenter(view) {

    private val repository = JobPositionRepositoryImpl(GithubJobsApi())

    fun getJobsList() {
        view.showProgressIndicator(true)

        launch(IoDispatcher) {
            val data = repository.getJobsList()
            view.getJobsListSuccess(data)
        }.invokeOnCompletion {
            view.showProgressIndicator(false)
        }
    }
}