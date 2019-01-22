package tk.epavlov.microsystem.boot.parsers.cdek

import okhttp3.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.parsers.Parser


@Service
class CdekParser : Parser {

    @Autowired
    lateinit var config: CdekConfig

    override suspend fun getTrack(trackId: String): TrackData? {
        val spend = System.currentTimeMillis()
        var cookieResponse = getRetrofit().getCookies(trackId).await()
        cookieResponse.headers().toMultimap().forEach { t, u ->
            u.forEach { _ ->
                println("$t: $u")
            }
        }
//        log.info("${cookieResponse.body()}")
//
//
//
//
//
//        var response = getRetrofit().getCdek("${System.currentTimeMillis() * 10}-xml", "Action=GetTrackingInfo&invoice=$trackId")
//        var data = response.await()
//        log.info("[RESPONSE: 0] $data")
//        if (!data.isSuccessful && data.code() == HttpStatus.TEMPORARY_REDIRECT.value()) {
//            response = getRetrofit().getCdek("${System.currentTimeMillis() * 10}-xml", "Action=GetTrackingInfo&invoice=$trackId")
//            data = response.await()
//            log.info("[RESPONSE: 1] $data")
//
//        }
//        data.headers().toMultimap().forEach { t, u ->
//            u.forEach {
//                println("$t: $u")
//            }
//        }
//        log.info("[RESPONSE] $data")
        return null
    }

    override fun getName() = config.name

    override fun getCode() = config.code

    override fun isEnabled(): Boolean = config.enabled


    private fun getRetrofit(): RetrofitCdek {
        return getRetrofit(config.url, RetrofitCdek::class.java)
    }

    override fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .cookieJar(object: CookieJar{
                    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
                        cookies.forEach {
                            println("${url.url()} ${it.name()} ${it.value()}")
                        }
                    }

                    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
                        return mutableListOf()
                    }

                })
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor { chain ->
                    val builder = chain.request().newBuilder()
                    chain.proceed(builder.build()).also {
                        log.info("intercept response: ${it.code()}")
                    }
                }

                .build()

    }
}