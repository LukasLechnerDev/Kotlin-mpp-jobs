package repository

import api.GithubJobsApi
import components.joblist.model.JobPosition

interface JobPositionRepository {
    suspend fun getJobsList(): List<JobPosition>
}

class JobPositionRepositoryImpl(private val apiService: GithubJobsApi) : JobPositionRepository {
    override suspend fun getJobsList(): List<JobPosition> {

        return apiService.fetchJobs().map {
            JobPosition.create(it)
        }
    }
}