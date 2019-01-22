package tk.epavlov.microsystem.boot.parsers.cdek.entity

data class Content(val receiverAddress: String = "",
                   val orderNumber: String = "",
                   val storageDateEnd: String = "",
                   val currentDateTimeCityTo: String = "",
                   val tariffDateEnd: String = "",
                   val canBeChanged: Boolean = false,
                   val cityFrom: CityFrom,
                   val cityTo: CityTo,
                   val trackingDetails: List<TrackingDetailsItem>?,
                   val receiverStockPhone: String = "",
                   val orderDate: String = "",
                   val status: Status)