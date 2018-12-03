package tk.epavlov.microsystem.boot.rest

import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.parsers.Parser
import java.time.LocalDateTime
import javax.annotation.PostConstruct

@RestController
class ParserController {
    val log = LoggerFactory.getLogger(ParserController::class.java)

    @Autowired
    lateinit var context: ApplicationContext

    private val  parsers : MutableList<Parser> = ArrayList()

    @PostConstruct
    fun init(){
        val parsersMap=  context.getBeansOfType(Parser::class.java)
        parsersMap.forEach { t, u ->
            log.info("$t: enabled = ${u.isEnabled()}")
            if (u.isEnabled()){
                parsers.add(u)
            }
        }
    }

    @GetMapping("tracking")
    fun getTimes(@RequestParam("trackId", required = true)  trackId: String): List<TrackData?> {
        log.info("get $trackId")
        val response = ArrayList<TrackData>()
        return runBlocking {
            val jobs =ArrayList<Deferred<TrackData?>> ()
            parsers.forEach {
                log.info("start: ${LocalDateTime.now()}")
                jobs.add(it.getTrackAsync(trackId))
            }
            //todo fix this
           return@runBlocking  withTimeout(1000) { return@withTimeout jobs.awaitAll().filterNotNull()}
        }
    }


}