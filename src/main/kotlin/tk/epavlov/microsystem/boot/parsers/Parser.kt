package tk.epavlov.microsystem.boot.parsers

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
                .build()
                .create(clazz)
    }
}
