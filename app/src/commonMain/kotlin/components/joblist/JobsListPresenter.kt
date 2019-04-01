package components.joblist

import api.GithubJobsApi
import api.IoDispatcher
import kotlinx.coroutines.launch
import presentation.BasePresenter
import presentation.JobsListView
import repository.JobPositionRepositoryImpl

class JobsListPresenter(
    private val view: JobsListView
) : BasePresenter(view) {

    private val repository = JobPositionRepositoryImpl(GithubJobsApi())

    fun getJobsList() {
        view.showProgressIndicator(true)

        launch(IoDispatcher) {
            repository.getJobsList().fold({
                view.getJobsListSuccess(it)
            }, {
                view.showError(Throwable("Loading Jobs failed"))
            })

        }.invokeOnCompletion {
            view.showProgressIndicator(false)
        }
    }
}