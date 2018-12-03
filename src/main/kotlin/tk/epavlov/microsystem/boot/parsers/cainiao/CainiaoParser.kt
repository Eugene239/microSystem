package tk.epavlov.microsystem.boot.parsers.cainiao

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.common.safe
import tk.epavlov.microsystem.boot.parsers.Parser
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@Service
class CainiaoParser : Parser, CommonInterface {
    companion object {
        const val PREFIX = "<textarea style=\"display: none;\" id=\"waybill_list_val_box\">"
        const val POSTFIX = "</textarea>"
        const val QUOT = "&quot;"
        const val LATEST_INFO = "latestTrackingInfo:"
        const val MAILNO = ",mailNo"
        const val TIMEZONE = ",timeZone:"
        const val DESC = "desc"
        const val STATUS = "status"
        const val TIME = "time"
        const val DATE_FORMAT = "yyyy-MM-dd HH:mm:SS"
        const val EMPTY = "RESULT_EMPTY"
        const val LOGITIC_ALERT = ",logisticsAlert"
        val formatter = SimpleDateFormat(DATE_FORMAT)
    }

    @Autowired
    private lateinit var config: CainiaoConfig

    //todo change suspend to blocking
    override suspend fun getTrack(trackId: String): TrackData? {
        val start = System.currentTimeMillis()
        var response: TrackData? = null

        log.debug("[getTrack]: $trackId")
        val body = getRetrofit(config.url, RetrofitCainiao::class.java)
                .getCainiao(trackId)
                .await().string()

        safe {
            var raw = body.splitToSequence("\n").filter { it.contains("waybill_list_val_box") }.firstOrNull()
            if (raw != null) {
                log.debug("[BEFORE] $raw")
                if (raw.contains(EMPTY)) return@safe
                log.info(raw)
                raw = replace(raw)
                log.info("[AFTER] $raw")
                val entity = gson.fromJson(raw, CainiaoEntity::class.java)
                val time = LocalDateTime.ofInstant(formatter.parse(entity.time).toInstant(), TimeZone.getDefault().toZoneId())
                log.debug(entity.toString())

                response = TrackData(trackId,
                        getName(),
                        getCode(),
                        entity.status ?: "",
                        entity.desc ?: "",
                        time,
                        LocalDateTime.now(),
                        System.currentTimeMillis() - start)
            }
        }
        log.info("[RESPONSE] $response")
        return response
    }

    public fun replace(rawText: String): String {
        return rawText
                .replace(PREFIX, "")
                .replace(POSTFIX, "")
                .replace(QUOT, "")
               // .split(LATEST_INFO)[1]
               // .split(MAILNO)[0]
               // .split(LOGITIC_ALERT)[0]

//                .replace("{", "{\"")
//                .replace("$DESC:", "$DESC\":\"")
//                .replace(",$STATUS:", "\",\"$STATUS\":\"")
//                .replace(",$TIME:", "\",\"$TIME\":\"")
//                .replace(",$TIMEZONE:", "\",$TIMEZONE\":\"")
//                .replace("}", "\"}")
                .trim()
    }

    override fun getName(): String = config.name
    override fun isEnabled(): Boolean = config.enabled
    override fun getCode(): Int = config.code

}