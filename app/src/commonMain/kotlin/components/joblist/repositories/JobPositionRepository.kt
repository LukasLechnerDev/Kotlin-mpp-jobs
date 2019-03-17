package repository

import api.GithubJobsApi
import components.joblist.model.JobPositionDto

interface JobPositionRepository {
    suspend fun getJobsList(): List<JobPositionDto>
}

class JobPositionRepositoryImpl(private val apiService: GithubJobsApi) : JobPositionRepository {
    override suspend fun getJobsList(): List<JobPositionDto> {
        return apiService.fetchJobs()
    }
}