import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import tk.epavlov.microsystem.boot.Application
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.parsers.cdek.CdekConfig
import tk.epavlov.microsystem.boot.parsers.cdek.CdekParser


@RunWith(SpringRunner::class)
@EnableConfigurationProperties
@ContextConfiguration(classes = [Application::class])
@SpringBootTest
@TestPropertySource(locations = ["classpath:application.yml"],
        properties = ["logging.level.tk.epavlov.microsystem:DEBUG"])

class CdekTest : CommonInterface {
    @Autowired
    lateinit var parser: CdekParser
    @Autowired
    lateinit var config: CdekConfig


    val track = "1105210273"
    val errorTrack = "oEIWOIWe213213"

    @Before
    fun setupLog() {
        log.info(config.toString())
    }

    @Test
    fun get() {
        runBlocking {
            val track = parser.getTrack(track)
            log.info(track.toString())
        }
    }

    @Test
    fun testNull() {
        runBlocking {
            val track = parser.getTrack(errorTrack)
            log.info(track.toString())
            assert(track == null)
        }
    }

    @Test
    fun testMillis(){
        println(System.currentTimeMillis())
    }


    @Test
    fun testRequest(){
        val client = OkHttpClient.Builder()
                .cookieJar(object : CookieJar{
                    var cookies = ArrayList<Cookie>()
                    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
                        cookies.forEach {
                            println("${it.name()} ${it.value()}")
                            this.cookies.add(it)
                        }
                    }

                    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
                        return cookies
                    }

                })
                .followRedirects(true)
                .followSslRedirects(true)
//                .addInterceptor { chain->
//                    val build = chain.request().newBuilder()
//                    chain.proceed(build.build())
//                }
                .build()

        val request = Request.Builder()
                .url("https://www.cdek.ru/track.html?order_id=1105210273%20")
                .get()
                .addHeader("cache-control", "no-cache")
                .build()

        val response = client.newCall(request).execute()
        log.info("$response")
    }
}