package api

import components.joblist.model.JobPositionDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class GithubJobsApi {

    private val client = HttpClient()

    suspend fun fetchJobs(): List<JobPositionDto> {
        val jsonResponse = client.get<String>("$baseUrl/positions.json?description=kotlin")
        return Json.nonstrict.parse(JobPositionDto.serializer().list,jsonResponse)
    }

    fun fetchJobsSync(): String {
        return "Jobs sync"
    }

    companion object {
        private const val baseUrl = "https://jobs.github.com"
    }
}