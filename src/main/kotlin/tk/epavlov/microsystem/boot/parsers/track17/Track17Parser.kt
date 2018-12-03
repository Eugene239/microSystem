package tk.epavlov.microsystem.boot.parsers.track17

import kotlinx.coroutines.Deferred
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.parsers.Parser
import tk.epavlov.microsystem.boot.parsers.track17.request.Data
import tk.epavlov.microsystem.boot.parsers.track17.request.Track17Request



@Service
class Track17Parser : Parser, CommonInterface {

    @Autowired
    lateinit var config: Track17Config

    override suspend fun getTrack(trackId: String): TrackData? {
        val spend = System.currentTimeMillis()
        val response= getRetrofit().get17Track( Track17Request("",arrayListOf(Data(trackId)))).await()
        println(response)
//        val client = OkHttpClient.Builder()
//                .addInterceptor {
//                    val request =it.request()
//                    request.headers().toMultimap().forEach { t, u ->
//                        println("$t: $u")
//                    }
//                   return@addInterceptor it.proceed(request)
//                }
//                .build()
//        var request = createPostRequest(Track17Request(arrayListOf(Data(trackId)),""),"")
//        val response = client.newCall(request).execute()
//        println(response.body()?.string())
        return null
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
        return  getRetrofit(config.url, Retrofit17Track::class.java)
    }

    private fun createPostRequest(track17Request: Track17Request, cookie: String?): Request {
        return Request.Builder()
                .url(config.url+"restapi/track")
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), gson.toJson(track17Request)))
                .header("accept-encoding", "deflate, br")
                .header("accept", "application/json, text/javascript, */*; q=0.01")
                .header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("cookie", "$cookie")
                .header("origin", "https://t.17track.net")
                .header("pragma", "no-cache")
                .header("referer", "https://t.17track.net/ru")
                .header("origin", "https://t.17track.net")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .header("x-requested-with", "XMLHttpRequest")
                .build()
    }

}
