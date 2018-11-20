package tk.epavlov.microsystem.boot.common

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomePage{

    @GetMapping("/")
    fun hello(): String{
        return "Hello, retard"
    }
}