package tk.epavlov.botapi.parsers



import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.log4j.LogManager
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.parsers.Parser
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashMap



class MainParser {
    private val log = LogManager.getLogger(MainParser::class.java)
    private val parserMap: HashMap<Int, Parser> = HashMap()
    private val pattern = Pattern.compile(".*[0-9]{5,}.*")

    init {
    //    val parser17Track = kodeinParser.lazy.instance<Parser17Track>().value
        //  parserMap[CainiaoParser.getCode()] = CainiaoParser
    //    parserMap[parser17Track.getCode()] = parser17Track
        // parserMap[ParserPochtaRu.getCode()] = ParserPochtaRu
    }

    suspend fun findTrack(text: String): List<TrackData> {
        val list = Collections.synchronizedList(ArrayList<TrackData>())
        val jobs = mutableListOf<Deferred<TrackData?>>()
        log.debug("findTrack text: $text")

//        parserMapper.getList().forEach {
//            parserMap[it.code]?.let { parser ->
//                log.debug("searching $text by ${it.name}")
//                jobs.add(parser.getTrackAsync(text))
//            }
//        }
        jobs.forEach {
            val track = it.await()
            track?.let {
                GlobalScope.launch {
                 //   trackServce.saveTrackData(it)
                }
                list.add(it)
            }
        }
        return list

    }


    /**
     * Use it for testing or then you know witch parser u want to use
     */
    suspend fun findTrack(trackId: String, parserCode: Int): TrackData? {
        parserMap[parserCode]?.let { parser ->
            return parser.getTrack(trackId)
        }
        return null
    }

}
