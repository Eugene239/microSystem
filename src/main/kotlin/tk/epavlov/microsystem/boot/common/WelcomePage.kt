package tk.epavlov.microsystem.boot.common

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@RestController
class WelcomePage{
    var startTime : Long = 0

    @PostConstruct
    fun init(){
        startTime= System.currentTimeMillis()
    }

    @GetMapping("/")
    fun hello(): String{
        val seconds = ChronoUnit.SECONDS.between (Instant.ofEpochMilli(startTime),Instant.ofEpochMilli(System.currentTimeMillis()))
        return "[UPTIME]: ${seconds/60/24}:${seconds/60%24} ${seconds%60}"
    }

    @PostMapping("restapi/track")
    fun postTest(request: HttpServletRequest): String{
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
}