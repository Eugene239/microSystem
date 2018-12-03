package tk.epavlov.microsystem.boot.parsers.pochta

import kotlinx.coroutines.Deferred
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.parsers.Parser
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class PochtaParser : Parser, CommonInterface{

    @Autowired
    lateinit var config: PochtaConfig

    //todo change suspend to blocking
    override suspend fun getTrack(trackId: String): TrackData? {
        val spend = System.currentTimeMillis()
        val response = getRetrofit().getPochtaTrack(trackId,System.currentTimeMillis()).await()
        log.info("response: $response")
        return if (response.list == null){
            null
        } else{
            return with(response) {
                val item = this.list?.get(0)
                val itemHistory  = item?.trackingItem?.trackingHistoryItemList?.get(0)
                TrackData(
                        item?.trackingItem?.barcode?:"",
                        getName(),
                        getCode(),
                        item?.trackingItem?.globalStatus?:"",
                        itemHistory?.humanStatus?:"",
                        LocalDateTime.parse(itemHistory?.date, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                        LocalDateTime.now(),
                        System.currentTimeMillis()-spend)
            }
        }
    }

    override fun getName() = config.name

    override fun getCode() = config.code

    override fun isEnabled(): Boolean = config.enabled


    private fun getRetrofit(): RetrofitPochta{
        return  getRetrofit(config.url, RetrofitPochta::class.java)
    }
}