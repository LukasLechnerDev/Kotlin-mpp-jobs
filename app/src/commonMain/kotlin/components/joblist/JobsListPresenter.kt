package components.joblist

import api.GithubJobsApi
import api.IoDispatcher
import common.UiState
import kotlinx.coroutines.launch
import presentation.BasePresenter
import presentation.JobsListView
import repository.JobPositionRepositoryImpl

class JobsListPresenter(
    private val view: JobsListView
) : BasePresenter(view) {

    private val repository = JobPositionRepositoryImpl(GithubJobsApi())

    fun getJobsList() {
        view.render(UiState.Loading())

        launch(IoDispatcher) {
            repository.getJobsList().fold({
                view.render(UiState.Success(it))
            }, {
                view.render(UiState.Error(Throwable("Loading Jobs failed")))
            })
        }
    }
}