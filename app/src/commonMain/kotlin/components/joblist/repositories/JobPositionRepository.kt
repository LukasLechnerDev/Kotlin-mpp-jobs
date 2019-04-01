package repository

import api.GithubJobsApi
import common.Outcome
import common.logDebug
import common.logException
import components.joblist.model.JobPosition
import datastore.JobsDataStore
import datastore.JobsDataStoreImpl

interface JobPositionRepository {
    suspend fun getJobsList(): Outcome<List<JobPosition>,Exception>
}

class JobPositionRepositoryImpl(
    private val apiService: GithubJobsApi,
    private val localDataStore: JobsDataStore = JobsDataStoreImpl
) : JobPositionRepository {

    private val tag = "JobPositionRepository"

    override suspend fun getJobsList(): Outcome<List<JobPosition>,Exception> {
        apiService.fetchJobs().fold({ jobsDto ->
            logDebug(tag, "Jobs loaded from API")
            val jobs = jobsDto.map { JobPosition.create(it) }
            localDataStore.set(jobs)
            return Outcome.success(jobs)
        },{
            logException(tag, "Something went wrong: ${it.message}", it)
            val localJobs = localDataStore.get()
            return if (localJobs == null) {
                Outcome.error(Exception("No local jobs stored"))
            } else{
                logDebug(tag, "Jobs loaded from local datasource")
                Outcome.success(localJobs)
            }
        })
    }
}