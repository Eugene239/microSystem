package tk.epavlov.microsystem.boot.parsers.cainiao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.common.safe
import tk.epavlov.microsystem.boot.parsers.Parser
import tk.epavlov.microsystem.boot.parsers.cainiao.entity.CainiaoEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class CainiaoParser : Parser {
    companion object {
        const val PREFIX = "<textarea style=\"display: none;\" id=\"waybill_list_val_box\">"
        const val POSTFIX = "</textarea>"
        const val QUOT = "&quot;"
        const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        const val EMPTY = "RESULT_EMPTY"
    }

    @Autowired
    private lateinit var config: CainiaoConfig

    override suspend fun getTrack(trackId: String): TrackData? {
        val start = System.currentTimeMillis()
        var response: TrackData? = null

        log.debug("[getTrack]: $trackId")
        val body = getRetrofit(config.url, RetrofitCainiao::class.java)
                .getCainiao(trackId)
                .await().string()

        safe {
            var raw = body.splitToSequence("\n").filter { it.contains("waybill_list_val_box") }.firstOrNull()
            if (!raw.isNullOrBlank()) {
                log.debug("RAW: $raw")
                raw = replace(raw)
                log.debug("REPLACE: $raw")
                val entity = gson.fromJson(raw, CainiaoEntity::class.java)
                if (entity.data!=null && entity.data.isNotEmpty()){
                    entity.data[0].latestTrackingInfo?.let {
                        response = TrackData(
                                id = trackId,
                                parser = getName(),
                                parserCode = getCode(),
                                status = it.status?:"",
                                text = it.desc?:"",
                                lastCheck = LocalDateTime.now(),
                                time = LocalDateTime.parse(it.time, DateTimeFormatter.ofPattern(DATE_FORMAT)),
                                spendTime = System.currentTimeMillis()- start)
                    }

                }
            }
        }
        log.info("[RESPONSE] $response TIMESPEND: ${System.currentTimeMillis() - start}")
        return response
    }

    fun replace(rawText: String): String {
        return rawText
                .replace(PREFIX, "")
                .replace(POSTFIX, "")
                .replace(QUOT, "\"")
                .trim()
    }

    override fun getName(): String = config.name
    override fun isEnabled(): Boolean = config.enabled
    override fun getCode(): Int = config.code

}