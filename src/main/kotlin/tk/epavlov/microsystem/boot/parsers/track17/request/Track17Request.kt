package tk.epavlov.microsystem.boot.parsers.track17.request

import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonPropertyOrder("guid", "data")
data class Track17Request(
        val guid: String,
        val data: List<Data>
)
data class Data(
        val num: String
)