package tk.epavlov.microsystem.boot.parsers.track17

import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.common.info
import tk.epavlov.microsystem.boot.parsers.Parser
import tk.epavlov.microsystem.boot.parsers.track17.request.Data
import tk.epavlov.microsystem.boot.parsers.track17.request.Track17Request
import tk.epavlov.microsystem.boot.parsers.track17.response.Track
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random


@Service
class Track17Parser : Parser, CommonInterface {
    private val random = Random(System.currentTimeMillis())
    @Autowired
    lateinit var config: Track17Config

    override suspend fun getTrack(trackId: String): TrackData? {
        log.debug("getting track: $trackId")
        val spend = System.currentTimeMillis()
        val request = Track17Request("", arrayListOf(Data(trackId)))
        log.debug("request: ${gson.toJson(request)}")
        var response = getRetrofit().getResponse(request).await()
        response.info()
        return null
    }

    suspend fun getTrackWihoutCookie(trackId: String): TrackData? {
        log.debug("getting track: $trackId")
        val spend = System.currentTimeMillis()
        val request = Track17Request("", arrayListOf(Data(trackId)))
        log.debug("request: ${gson.toJson(request)}")
        var response = getRetrofit().get17Track(request).await()
        log.debug("response: $response")
        if (response.dat.isEmpty()) {
            return null
        }
        var track17 = response.dat[0].track
        if (track17 != null) {
            return checkTrack(track17, trackId, spend)
        } else {
            log.debug("trying second request")
            //sending second request
            response = GlobalScope.async {
                delay(500 + random.nextInt(1000))
                return@async getRetrofit().get17Track(request).await()
            }.await()
            log.debug("second response: $response")
            if (response.dat.isEmpty()) {
                return null
            }
            track17 = response.dat[0].track
            return if (track17 != null) {
                checkTrack(track17,trackId, spend)
            }else{
                null
            }
        }
    }

    override fun getName(): String {
        return config.name
    }

    override fun getCode(): Int {
        return config.code
    }

    override fun isEnabled(): Boolean {
        return config.enabled
    }

    override suspend fun getTrackAsync(trackId: String): Deferred<TrackData?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getRetrofit(): Retrofit17Track {
        return getRetrofit(config.url, Retrofit17Track::class.java)
    }

    private fun checkTrack(response: Track, trackId: String, spend: Long): TrackData? {
        log.info("checking track $response")
        return TrackData(
                id = trackId,
                parser = getName(),
                parserCode = getCode(),
                status = response.z0.z,
                text = "${response.z0.c}. ${response.z0.d}",
                time = LocalDateTime.parse(response.z0.a, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                lastCheck = LocalDateTime.now(),
                spendTime = System.currentTimeMillis() -spend
        )
    }

}
