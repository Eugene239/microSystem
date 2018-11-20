package tk.epavlov.microsystem.boot.rest

import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class ParserController {
    val log = LoggerFactory.getLogger(ParserController::class.java)

    @GetMapping("test")
    fun getTimes(): Deferred<MutableList<String>> {
        log.info("test")
        val deferred = CompletableDeferred<MutableList<String>>()
        val list = mutableListOf<String>()

       GlobalScope.async {
            list.add(LocalDateTime.now().toString())

            val one = async { delay(2000); LocalDateTime.now().toString() }

            val two = async { delay(2000); LocalDateTime.now().toString() }

            list.apply {
                this.addAll(kotlin.collections.mutableListOf(one.await(), two.await()))
            }
            deferred.complete(list)
        }
        return deferred

    }

    @GetMapping("test2")
    suspend fun getTimes2(): List<String> {
        log.info("test")
        var list = mutableListOf<String>()

        list.add(LocalDateTime.now().toString())

        list.add(LocalDateTime.now().toString())

        list.add(LocalDateTime.now().toString())

        return list
    }
}