package tk.epavlov.microsystem.boot.common

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

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
                seconds = seconds,
                minutes = seconds/60%60,
                hours = seconds/60/60%24,
                days = ChronoUnit.DAYS.between(Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(System.currentTimeMillis())),
                startDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault())
        )
    }

    @PostMapping("restapi/track")
    fun postTest(request: HttpServletRequest): String {
        val list = request.headerNames.toList()
        list.forEach {
            println("$it: ${request.getHeader(it)}")
        }
        println()
        request.parameterMap.forEach { t, u ->
            println("$t: $u")
        }
        return "kuka"
    }

    data class Uptime(
            val seconds: Long = 0,
            val minutes: Long = 0,
            val hours: Long = 0,
            val days: Long = 0,
            val startDateTime : LocalDateTime
    )
}