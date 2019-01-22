package tk.epavlov.microsystem.boot.common

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

import java.util.ArrayList

class AcceptCookieJar : CookieJar {

    private var cookies: List<Cookie>? = null

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies = cookies
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return if (cookies != null) cookies!! else ArrayList()
    }
}