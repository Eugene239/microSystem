package tk.epavlov.microsystem.boot.parsers.cdek

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import tk.epavlov.microsystem.boot.parsers.cdek.entity.CdekResponse

internal interface RetrofitCdek {
    @POST("ajax.php")
    fun getCdek(@Query("JsHttpRequest") xmlTimeStamp: String, @Body binary: String, @Header("Cookie") cookies: String? = null): Deferred<Response<CdekResponse>>

    @GET(value="track.html")
    @Headers("cache-control:no-cache")
    fun getCookies(@Query("order_id") trackId: String): Deferred<Response<ResponseBody>>


    //get https://www.cdek.ru/track.html?order_id=%orderId% getting cookies, using it

    /* simple curl:
    curl 'https://www.cdek.ru/ajax.php?JsHttpRequest=15481566984044-xml' \
    -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36' \
    -H 'Content-Type: application/octet-stream' \
    -H 'Cookie: ipp_key=v1548156523526/v3394bd3d50b9af48aec082163aeca6afa04ab3/7qpwRGd3Jxks7n1xgSpGJg==; ipp_uid=1548156523524/Xgfv8wLpDrJbshYP/5O+FG+ixijVq7DUfHSXINQ==' \
    --data-binary 'Action=GetTrackingInfo&invoice=1105210273' --compressed
     */

}