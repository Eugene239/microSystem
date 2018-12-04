package tk.epavlov.microsystem.boot.parsers.cainiao.entity
import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("destCpList")
                    val destCpList: List<DestCpListItem>?,
                    @SerializedName("latestTrackingInfo")
                    val latestTrackingInfo: LatestTrackingInfo?,
                    @SerializedName("bizType")
                    val bizType: String? = "",
                    @SerializedName("statusDesc")
                    val statusDesc: String? = "",
                    @SerializedName("showEstimateTime")
                    val showEstimateTime: Boolean? = false,
                    @SerializedName("section1")
                    val section1: Section?,
                    @SerializedName("section2")
                    val section2: Section?,
                    @SerializedName("mailNo")
                    val mailNo: String? = "",
                    @SerializedName("destCountry")
                    val destCountry: String? = "",
                    @SerializedName("hasRefreshBtn")
                    val hasRefreshBtn: Boolean? = false,
                    @SerializedName("allowRetry")
                    val allowRetry: Boolean? = false,
                    @SerializedName("success")
                    val success: Boolean? = false,
                    @SerializedName("originCountry")
                    val originCountry: String? = "",
                    @SerializedName("syncQuery")
                    val syncQuery: Boolean? = false,
                    @SerializedName("shippingTime")
                    val shippingTime: Int? = 0,
                    @SerializedName("cachedTime")
                    val cachedTime: String? = "",
                    @SerializedName("status")
                    val status: String? = "")


data class CainiaoEntity(@SerializedName("timeSeconds")
                         val timeSeconds: Double? = 0.0,
                         @SerializedName("data")
                         val data: List<DataItem>?,
                         @SerializedName("success")
                         val success: Boolean? = false)


data class DetailListItem(@SerializedName("timeZone")
                          val timeZone: String? = "",
                          @SerializedName("time")
                          val time: String? = "",
                          @SerializedName("desc")
                          val desc: String? = "",
                          @SerializedName("status")
                          val status: String? = "")


data class DestCpListItem(@SerializedName("cpCode")
                          val cpCode: String? = "",
                          @SerializedName("country")
                          val country: String? = "",
                          @SerializedName("cpName")
                          val cpName: String? = "")


data class Section(@SerializedName("companyName")
                   val companyName: String? = "",
                   @SerializedName("detailList")
                   val detailList: List<DetailListItem>?,
                   @SerializedName("countryName")
                   val countryName: String? = "",
                   @SerializedName("url")
                   val url: String? = "")


data class LatestTrackingInfo(@SerializedName("timeZone")
                              val timeZone: String? = "",
                              @SerializedName("time")
                              val time: String? = "",
                              @SerializedName("desc")
                              val desc: String? = "",
                              @SerializedName("status")
                              val status: String? = "")


