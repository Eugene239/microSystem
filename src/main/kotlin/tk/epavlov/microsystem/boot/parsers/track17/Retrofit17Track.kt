package tk.epavlov.microsystem.boot.parsers.track17

import kotlinx.coroutines.Deferred
import retrofit2.Response

import retrofit2.http.*
import tk.epavlov.microsystem.boot.parsers.track17.request.Track17Request
import tk.epavlov.microsystem.boot.parsers.track17.response.Track17Response

interface Retrofit17Track{

    @Headers(
            "accept: application/json, text/javascript, */*; q=0.01",
            "accept-encoding: deflate, br",
            "accept-language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
            "cache-control: no-cache",
            "content-type: application/x-www-form-urlencoded; charset=UTF-8",
            "cookie: _yq_bid=G-FCE87E98A3CA7E51; __cfduid=d7c4920a5134b643a2cd1b19e620bbc751543611461; v5_TranslateLang=ru; Last-Event-ID=657572742f3161332f35383862393636363736312f726573752d616964656d2d756e656d2d6e776f64706f72642d717920616964656d2d756e656d2d6e776f64706f726420756e656d2d6e776f64706f7264a110032e3121ec79c1f",
            "dnt: 1",
            "origin: https://t.17track.net",
            "pragma: no-cache",
            "referer: https://t.17track.net/ru",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36",
            "x-compress: null",
            "x-requested-with: XMLHttpRequest"
    )
    @POST("restapi/track")
    fun get17Track(@Body request:Track17Request): Deferred<Track17Response>

    @Headers(
            "accept: application/json, text/javascript, */*; q=0.01",
            "accept-encoding: deflate, br",
            "accept-language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
            "cache-control: no-cache",
            "content-type: application/x-www-form-urlencoded; charset=UTF-8",
            "dnt: 1",
            "origin: https://t.17track.net",
            "pragma: no-cache",
            "referer: https://t.17track.net/ru",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36",
            "x-compress: null",
            "x-requested-with: XMLHttpRequest"
    )
    @POST("restapi/track")
    fun getResponse(@Body request:Track17Request): Deferred<Response<Track17Response>>


}