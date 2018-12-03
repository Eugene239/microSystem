package tk.epavlov.microsystem.boot.parsers.track17.response

import com.google.gson.annotations.SerializedName

data class Track17Response(
        @SerializedName("dat")
        val dat: List<Dat>,
        @SerializedName("g")
        val g: String,
        @SerializedName("msg")
        val msg: String,
        @SerializedName("ret")
        val ret: Int
)