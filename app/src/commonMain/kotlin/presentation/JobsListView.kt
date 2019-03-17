package presentation

import components.joblist.model.JobPositionDto

interface JobsListView: BaseView {
    fun getJobsListSuccess(jobs: List<JobPositionDto>)
}