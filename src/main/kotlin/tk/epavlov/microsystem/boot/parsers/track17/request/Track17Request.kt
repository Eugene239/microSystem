package tk.epavlov.microsystem.boot.parsers.track17.request


data class Track17Request(
        val guid: String,
        val data: List<Data>
)
data class Data(
        val num: String
)