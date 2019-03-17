package components.joblist.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobPositionDto(
    @SerialName("id")
    val id: String,

    @SerialName("company")
    val company: String,

    @SerialName("location")
    val location: String,

    @SerialName("title")
    val title: String)
