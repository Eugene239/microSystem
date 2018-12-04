package tk.epavlov.microsystem.boot.parsers

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.common.TrackData
import java.util.*
import javax.annotation.PostConstruct

@Service
class ParserService : CommonInterface {
    @Autowired
    lateinit var context: ApplicationContext

    private val parsers: MutableList<Parser> = ArrayList()

    @PostConstruct
    fun init() {
        val parsersMap = context.getBeansOfType(Parser::class.java)
        parsersMap.forEach { t, u ->
            log.info("$t: enabled = ${u.isEnabled()}")
            if (u.isEnabled()) {
                parsers.add(u)
            }
        }
    }

    fun getTrackInfo(trackId: String): List<TrackData> {
        log.info("get $trackId")
        return runBlocking {
            val jobs = ArrayList<Deferred<TrackData?>>()
            parsers.forEach {
                jobs.add(it.getTrackAsync(trackId))
            }
            jobs.awaitAll().filterNotNull()
        }
    }
}