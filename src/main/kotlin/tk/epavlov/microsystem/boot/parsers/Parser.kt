package tk.epavlov.microsystem.boot.parsers

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tk.epavlov.microsystem.boot.common.TrackData

interface Parser {
    suspend fun getTrack(trackId: String): TrackData?
    fun getName(): String
    fun getCode(): Int
    fun isEnabled(): Boolean
    suspend fun getTrackAsync(trackId: String): Deferred<TrackData?>

    val gson: Gson
        get() = Gson()

    fun <T> getRetrofit (url: String, clazz: Class<T>): T {
        return Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
                .create(clazz)
    }
}
