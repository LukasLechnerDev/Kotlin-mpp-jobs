package datastore

import com.russhwolf.settings.Settings
import common.settingsFactory
import components.joblist.model.JobPosition
import components.joblist.model.JobPositionDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

interface JobsDataStore{
    fun set(jobs: List<JobPosition>)
    fun get(): List<JobPosition>?
}

object JobsDataStoreImpl: JobsDataStore{

    val settings: Settings = settingsFactory().create("job_list")

    override fun get(): List<JobPosition>?{
        val jobsJson = settings.getString("jobs")
        val jobDtos =  Json.nonstrict.parse(JobPositionDto.serializer().list, jobsJson)
        return jobDtos.map { JobPosition.create(it) }
    }

    override fun set(jobs: List<JobPosition>) {
        val jobDtos = jobs.map { it.toDto() }
        val jobJson = Json.stringify(JobPositionDto.serializer().list, jobDtos)
        settings.putString("jobs", jobJson)
    }
}
