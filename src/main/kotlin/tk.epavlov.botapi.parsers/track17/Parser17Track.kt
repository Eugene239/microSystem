//package tk.epavlov.botapi.parsers.track17
//
//import com.epavlov.botapi.db.ParserDAO
//import tk.epavlov.microsystem.boot.parsers.Parser
//import tk.epavlov.botapi.parsers.kodeinParser
//import tk.epavlov.botapi.parsers.track17.entity.Dat
//import tk.epavlov.botapi.parsers.track17.entity.T17Num
//import tk.epavlov.botapi.parsers.track17.entity.T17Request
//import tk.epavlov.botapi.parsers.track17.entity.track17
//import com.epavlov.common.botapi.entity.TrackData
//import com.epavlov.common.botapi.utils.gson
//import com.github.salomonbrys.kodein.instance
//import com.github.salomonbrys.kodein.lazy
//import com.squareup.okhttp.*
//import io.webfolder.cdp.Launcher
//import io.webfolder.cdp.exception.ElementNotFoundException
//import kotlinx.coroutines.experimental.Deferred
//import kotlinx.coroutines.experimental.async
//import kotlinx.coroutines.experimental.runBlocking
//import org.apache.log4j.LogManager
//import java.time.LocalDateTime
//import java.util.*
//import kotlin.collections.ArrayList
//
//
//class Parser17Track : Parser {
//    private val log = LogManager.getLogger(Parser17Track::class.java)
//    private val url = ParserDAO.get(getCode())?.url
//
//    private fun getTrack(trackId: String, guid: String): String = runBlocking {
//        return@runBlocking getTrack(trackId, guid)
//    }
//
//    fun getTracks(tracks: List<String>): List<TrackData> {
//        val launcher = Launcher()
//
//        val sessionFactory = launcher.launch(listOf("--headless", "--disable-gpu"))
//        val session = sessionFactory.create()
//        val join = tracks.joinToString(",")
//
//        session.navigate("https://t.17track.net/ru#nums=$join")
//        session.waitDocumentReady()
//
//        //w8 to load data
//        session.waitUntil {
//            return@waitUntil try {
//                session.getText("//div[contains(@class, 'yqcr-loading-cell')]") == null
//            } catch (e: ElementNotFoundException) {
//                log.error(e.toString())
//                true
//            }
//        }
//        val cookie = session.command.network.allCookies.joinToString(";") { c -> "${c.name}:${c.value}" }
//        val client = OkHttpClient()
//        val req = createPostRequest(T17Request("", tracks.map { T17Num(it) }), cookie)
//        Thread.sleep(500 + Random().nextInt(1000).toLong())
//        val response = client.newCall(req).execute()
//        val json = response.body().string()
//        log.debug("[RESPONSE]: $json")
//
//        val track17 = gson.fromJson(json, track17::class.java)
//
//        session.close()
//        launcher.kill()
//
//        return if (track17.ret != 1) {
//            log.error(json)
//            emptyList()
//        } else {
//            track17.toTrackDataList()
//        }
//    }
//
//    private fun createPostRequest(t17request: T17Request, cookie: String?): Request {
//        return Request.Builder()
//                .url(url)
//                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), gson.toJson(t17request)))
//                .header("accept-encoding", "deflate, br")
//                .header("accept", "application/json, text/javascript, */*; q=0.01")
//                .header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
//                .header("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .header("cookie", "$cookie")
//                .header("origin", "https://t.17track.net")
//                .header("pragma", "no-cache")
//                .header("referer", "https://t.17track.net/ru")
//                .header("origin", "https://t.17track.net")
//                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
//                .header("x-requested-with", "XMLHttpRequest")
//                .build()
//    }
//
//    override suspend fun getTrack(trackId: String): TrackData? {
//        return getTracks(listOf(trackId)).firstOrNull { it.id == trackId }
//    }
//
//
//    private fun track17.toTrackDataList(): List<TrackData> {
//        val list = ArrayList<TrackData>()
//        this.dat.forEach { dat ->
//            dat.toTrackData()?.let {
//
//                list.add(it)
//            }
//        }
//
//        return list
//    }
//
//    private fun Dat.toTrackData(): TrackData? {
//        return if (this.track != null) {
//            TrackData(this.no,
//                    LocalDateTime.now().toString(),
//                    0,
//                    getName(),
//                    getCode(),
//                    if (track.z0 != null) track.z0.z else "",
//                    if (track.z0 != null) track.z0.c else "",
//                    track.ylt1,
//                    LocalDateTime.now().toString())
//        } else {
//            this.track
//        }
//    }
//
//    override fun getName(): String {
//        return "17TRACK"
//    }
//
//    override fun getCode(): Int {
//        return 2
//    }
//
//    override suspend fun getTrackAsync(trackId: String): Deferred<TrackData?> {
//        return async {
//            getTrack(trackId)
//        }
//    }
//}
//
//fun main(args: Array<String>) {
//    runBlocking {
//        val track17 = kodeinParser.lazy.instance<Parser17Track>().value
//        println(track17.getTrack("F3030118022200Z3"))
////    val tracks = kodeinDB.lazy.instance<TrackMapper>().value.getListByParser(track17.getCode())//.subList(110, 120)
////    val nulls = ArrayList<String>()
////    runBlocking {
////        tracks.forEach {
////            if (track17.getTrack(it.trackId.toUpperCase()) == null) {
////                nulls.add(it.trackId)
////            }
////        }
////        println(nulls)
////        //  track17.getTrack("RB498570800SG")
////    }
//        System.exit(-1)
//    }
//}
