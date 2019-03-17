package sample

import android.app.Application
import api.GithubJobsApi
import repository.JobPositionRepository
import repository.JobPositionRepositoryImpl

class MppJobsApplication : Application() {

    private val api: GithubJobsApi = GithubJobsApi()

    val jobPositionsRepository: JobPositionRepository by lazy {
        JobPositionRepositoryImpl(api)
    }
}