package tk.epavlov.microsystem.boot.parsers.track17.response

import com.google.gson.annotations.SerializedName

data class Dat(
        @SerializedName("delay")
        val delay: Int,
        @SerializedName("no")
        val no: String,
        @SerializedName("track")
        val track: Track,
        @SerializedName("yt")
        val yt: Any
)