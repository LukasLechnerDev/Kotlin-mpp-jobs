package components.joblist.model


data class JobPosition(val id: String, val company: String, val location: String, val title: String, val type: String){

    fun toDto(): JobPositionDto {
        return JobPositionDto(id, company, location, title, type)
    }

    companion object {
        fun create(jobPositionDto: JobPositionDto): JobPosition {
            return JobPosition(jobPositionDto.id, jobPositionDto.company, jobPositionDto.location, jobPositionDto.title, jobPositionDto.type)
        }
    }
}