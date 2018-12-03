package tk.epavlov.microsystem.boot.common

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class WelcomePage{

    @GetMapping("/")
    fun hello(): String{
        return "Hello, retard"
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