package tk.epavlov.microsystem.boot.parsers.track17.response

import com.google.gson.annotations.SerializedName

data class Track(
        @SerializedName("b")
        val b: Int,
        @SerializedName("c")
        val c: Int,
        @SerializedName("e")
        val e: Int,
        @SerializedName("f")
        val f: Int,
        @SerializedName("hs")
        val hs: Int,
        @SerializedName("is1")
        val is1: Int,
        @SerializedName("is2")
        val is2: Int,
        @SerializedName("ln1")
        val ln1: String,
        @SerializedName("ln2")
        val ln2: String,
        @SerializedName("w1")
        val w1: Int,
        @SerializedName("w2")
        val w2: Int,
        @SerializedName("ygt1")
        val ygt1: Int,
        @SerializedName("ygt2")
        val ygt2: Int,
        @SerializedName("ylt1")
        val ylt1: String,
        @SerializedName("ylt2")
        val ylt2: String,
        @SerializedName("yt")
        val yt: Any,
        @SerializedName("z0")
        val z0: Z0,
        @SerializedName("z1")
        val z1: List<Z1>,
        @SerializedName("z2")
        val z2: List<Z2>,
        @SerializedName("zex")
        val zex: Zex
)