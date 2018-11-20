package tk.epavlov.microsystem.boot.common

import java.time.LocalDateTime

data class TrackData(
        var id: String,
        var parser: String,
        var parserCode: Int,
        var status: String,
        var text: String,
        var time: LocalDateTime?,
        var lastCheck: LocalDateTime?,
        var spendTime: Long
)