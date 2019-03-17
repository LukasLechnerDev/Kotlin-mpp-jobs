package components.joblist.model


data class JobPosition(val id: String, val company: String, val location: String, val title: String){

    companion object {
        fun create(jobPositionDto: JobPositionDto): JobPosition {
            return JobPosition(jobPositionDto.id, jobPositionDto.company, jobPositionDto.location, jobPositionDto.title)
        }
    }
}