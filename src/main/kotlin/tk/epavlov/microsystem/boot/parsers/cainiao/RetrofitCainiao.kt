package tk.epavlov.microsystem.boot.parsers.cainiao

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitCainiao {
    @GET("detail.htm")
    fun getCainiao(@Query("mailNoList") trackId: String): Deferred<ResponseBody>

}
