package presentation

import components.joblist.model.JobPosition

interface JobsListView: BaseView {
    fun getJobsListSuccess(jobs: List<JobPosition>)
}