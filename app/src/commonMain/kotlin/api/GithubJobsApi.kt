package api

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class GithubJobsApi {

    private val client = HttpClient()

    suspend fun fetchJobs(): String {
        return client.get<String>("$baseUrl/positions.json?description=python&location=new+york")
    }

    fun fetchJobsSync(): String {
        return "Jobs sync"
    }

    companion object {
        private const val baseUrl = "https://jobs.github.com"
    }
}