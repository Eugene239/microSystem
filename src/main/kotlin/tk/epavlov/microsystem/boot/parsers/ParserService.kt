package tk.epavlov.microsystem.boot.parsers

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.common.TrackData
import java.util.*
import java.util.regex.Pattern
import javax.annotation.PostConstruct

@Service
class ParserService : CommonInterface {
    @Autowired
    lateinit var context: ApplicationContext

    private val parsers: MutableList<Parser> = ArrayList()

    @Value("\${parser.regexp}")
    lateinit var regexp: String

    private lateinit var pattern: Pattern


    @PostConstruct
    fun init() {
        val parsersMap = context.getBeansOfType(Parser::class.java)
        pattern = Pattern.compile(regexp)
        parsersMap.forEach { t, u ->
            log.info("$t: enabled = ${u.isEnabled()}")
            if (u.isEnabled()) {
                parsers.add(u)
            }
        }
    }

    @Cacheable(value = ["trackInfo"])
    fun getTrackInfo(trackId: String): List<TrackData> {
        log.debug("get $trackId")
        if (!pattern.matcher(trackId).matches()) {
            log.warn("NOT MATCHED REGEXP: $regexp")
            return emptyList()
        }

        return runBlocking {
            val jobs = ArrayList<Deferred<TrackData?>>()
            parsers.forEach {
                jobs.add(it.getTrackAsync(trackId))
            }
            jobs.awaitAll().filterNotNull().sortedBy { it.spendTime }
        }
    }
}