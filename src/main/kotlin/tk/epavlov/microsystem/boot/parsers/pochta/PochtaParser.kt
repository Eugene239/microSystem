package tk.epavlov.microsystem.boot.parsers.pochta

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.parsers.Parser
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class PochtaParser : Parser {

    @Autowired
    lateinit var config: PochtaConfig

    override suspend fun getTrack(trackId: String): TrackData? {
        val spend = System.currentTimeMillis()
        val response = getRetrofit().getPochtaTrack(trackId, System.currentTimeMillis()).await()
        log.info("trackPochta: $response")
        val track = if (response.list == null) {
            null
        } else {
            try {
                with(response) {
                    val item = this.list?.get(0)
                    val itemHistory = item?.trackingItem?.trackingHistoryItemList?.get(0)
                    TrackData(
                            item?.trackingItem?.barcode ?: "",
                            getName(),
                            getCode(),
                            item?.trackingItem?.globalStatus ?: "",
                            itemHistory?.humanStatus ?: "",
                            LocalDateTime.parse(itemHistory?.date ?: "", DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                            LocalDateTime.now(),
                            System.currentTimeMillis() - spend)
                }
            } catch (e: Exception) {
                log.error("${getName()} $trackId $response ERROR: $e")
                e.printStackTrace()
                return null
            }
        }
        log.info("[RESPONSE] $track")
        return track
    }

    override fun getName() = config.name

    override fun getCode() = config.code

    override fun isEnabled(): Boolean = config.enabled


    private fun getRetrofit(): RetrofitPochta {
        return getRetrofit(config.url, RetrofitPochta::class.java)
    }
}