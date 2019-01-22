package tk.epavlov.microsystem.boot.parsers

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tk.epavlov.microsystem.boot.common.AcceptCookieJar
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.common.TrackData

interface Parser : CommonInterface {
    suspend fun getTrack(trackId: String): TrackData?
    fun getName(): String
    fun getCode(): Int
    fun isEnabled(): Boolean

    fun getTrackAsync(trackId: String): Deferred<TrackData?> = GlobalScope.async {
        try {
            withTimeout(5000) { getTrack(trackId) }
        } catch (e: TimeoutCancellationException) {
            log.error("timeout ${getName()} getTrackAsync: $trackId")
            return@async null
        }
    }

    val gson: Gson
        get() = Gson()

    fun <T> getRetrofit(url: String, clazz: Class<T>): T {
        return Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(getClient())
                .build()
                .create(clazz)
    }

    fun getClient(): OkHttpClient{
        return OkHttpClient()
    }
}
