package tk.epavlov.microsystem.boot.common

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import javax.annotation.PostConstruct

@RestController
class WelcomePage {
    var startTime: Long = 0

    @PostConstruct
    fun init() {
        startTime = System.currentTimeMillis()
    }

    @GetMapping("/")
    fun hello(): Uptime {
        val seconds = ChronoUnit.SECONDS.between(Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(System.currentTimeMillis()))
        return Uptime(
                seconds = seconds%60,
                minutes = seconds/60%60,
                hours = seconds/60/60%24,
                days = ChronoUnit.DAYS.between(Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(System.currentTimeMillis())),
                startDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault())
        )
    }

    data class Uptime(
            val seconds: Long = 0,
            val minutes: Long = 0,
            val hours: Long = 0,
            val days: Long = 0,
            val startDateTime : LocalDateTime
    )
}