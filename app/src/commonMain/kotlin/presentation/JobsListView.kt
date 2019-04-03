package presentation

import common.UiState
import components.joblist.model.JobPosition

interface JobsListView: BaseView{
    fun render(uiState: UiState<List<JobPosition>>)
}