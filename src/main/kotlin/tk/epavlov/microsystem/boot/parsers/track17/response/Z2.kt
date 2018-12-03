package tk.epavlov.microsystem.boot.parsers.track17.response

import com.google.gson.annotations.SerializedName

data class Z2(
        @SerializedName("a")
        val a: String,
        @SerializedName("b")
        val b: Any,
        @SerializedName("c")
        val c: String,
        @SerializedName("d")
        val d: String,
        @SerializedName("z")
        val z: String
)