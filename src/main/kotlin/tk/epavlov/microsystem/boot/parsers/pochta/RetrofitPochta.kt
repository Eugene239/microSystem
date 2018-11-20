package tk.epavlov.microsystem.boot.parsers.pochta

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitPochta{

    @GET("tracking?p_p_id=trackingPortlet_WAR_portalportlet&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_resource_id=getList&p_p_cacheability=cacheLevelPage&p_p_col_id=column-1&p_p_col_pos=1&p_p_col_count=2")
    fun getPochtaTrack(@Query("barcodeList") barcode: String, @Query("_") timeMillis:Long ): Deferred<TrackPochta>
}