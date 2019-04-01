package api

import common.Outcome
import components.joblist.model.JobPositionDto
import io.ktor.client.HttpClient
import io.ktor.client.features.BadResponseStatusException
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class GithubJobsApi {

    private val client = HttpClient()

    suspend fun fetchJobs(): Outcome<List<JobPositionDto>,Exception> {
        return try {
            val jsonResponse = client.get<String>("$baseUrl/positions.json?description=kotlin")
            val parsedResponse = Json.nonstrict.parse(JobPositionDto.serializer().list, jsonResponse)
            Outcome.success(parsedResponse)
        } catch (exception: BadResponseStatusException) {
            Outcome.error(exception)
        } catch (exception: Exception) {
            Outcome.error(exception)
        }
    }

    companion object {
        private const val baseUrl = "https://jobs.github.com"
    }
}