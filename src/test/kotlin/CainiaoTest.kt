import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.logging.LogLevel
import org.springframework.boot.logging.LoggingSystem
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.junit4.SpringRunner
import tk.epavlov.microsystem.boot.Application
import tk.epavlov.microsystem.boot.parsers.cainiao.CainiaoConfig
import tk.epavlov.microsystem.boot.parsers.cainiao.CainiaoParser
import java.util.logging.Level
import java.util.logging.Logger

@RunWith(SpringRunner::class)
@EnableConfigurationProperties
@ContextConfiguration(classes = [Application::class])
@SpringBootTest
@TestPropertySource(locations=["classpath:application.yaml"],
        properties = ["logging.level.tk.epavlov.microsystem:DEBUG"])

class CainiaoTest{
    @Autowired
    lateinit var parser : CainiaoParser
    @Autowired
    lateinit var config: CainiaoConfig

    val logger  = LoggerFactory.getLogger(javaClass::class.java)

    val track = "RP052760925CN"

    @Before
    fun setupLog(){
        logger.info(config.toString())
    }

    @Test
    fun get() {
        runBlocking {
            val track= parser.getTrack(track)
            print(track)
        }
    }

    @Test
    fun testReplace(){
        val text= "{\"data\":[{\"allowRetry\":false,\"bizType\":\"P2P\",\"cachedTime\":\"2018-11-15 12:09:49\",\"destCountry\":\"Russian Federation\",\"destCpList\":[{\"country\":\"Russian Federation\",\"cpCode\":\"POSTRU\",\"cpName\":\"POSTRU\"}],\"hasRefreshBtn\":true,\"latestTrackingInfo\":{\"desc\":\"[RU,194358]Delivery,Delivery to the addressee\",\"status\":\"SIGNIN\",\"time\":\"2018-11-13 19:46:22\",\"timeZone\":\"\"},\"mailNo\":\"RP052760925CN\",\"originCountry\":\"China\",\"originCpList\":[],\"section1\":{\"countryName\":\"China\",\"detailList\":[]},\"section2\":{\"companyName\":\"Russia  Post\",\"countryName\":\"Russian Federation\",\"detailList\":[{\"desc\":\"[RU,194358]Delivery,Delivery to the addressee\",\"status\":\"SIGNIN\",\"time\":\"2018-11-13 19:46:22\",\"timeZone\":\"\"},{\"desc\":\"[俄罗斯]【俄罗斯】已签收\",\"status\":\"\",\"time\":\"2018-11-13 19:46:00\",\"timeZone\":\"\"},{\"desc\":\"[俄罗斯]【俄罗斯】正在投递\",\"status\":\"\",\"time\":\"2018-11-12 16:20:00\",\"timeZone\":\"\"},{\"desc\":\"[RU,194358]Processing,Arrival at delivery office\",\"status\":\"ARRIVED_AT_DEST_COUNTRY\",\"time\":\"2018-11-12 11:44:36\",\"timeZone\":\"\"},{\"desc\":\"[俄罗斯]已到达寄达地\",\"status\":\"\",\"time\":\"2018-11-12 11:44:00\",\"timeZone\":\"\"},{\"desc\":\"[RU,200960]Processing,Departure from inward office of exchange\",\"status\":\"\",\"time\":\"2018-11-12 07:31:12\",\"timeZone\":\"\"},{\"desc\":\"[RU,200974]Processing,Sorting\",\"status\":\"\",\"time\":\"2018-11-11 20:52:45\",\"timeZone\":\"\"},{\"desc\":\"[RU,200983]Processing,Sorting\",\"status\":\"\",\"time\":\"2018-11-11 20:52:13\",\"timeZone\":\"\"},{\"desc\":\"[RU,200960]Processing,Arrival at inward office of exchange\",\"status\":\"\",\"time\":\"2018-11-11 18:10:12\",\"timeZone\":\"\"},{\"desc\":\"[RU,190960]Processing,Departure from inward office of exchange\",\"status\":\"\",\"time\":\"2018-11-11 16:52:29\",\"timeZone\":\"\"},{\"desc\":\"[RU,190960]Processing,Arrival at inward office of exchange\",\"status\":\"\",\"time\":\"2018-11-11 14:01:37\",\"timeZone\":\"\"},{\"desc\":\"[RU,620966]Processing,Departure from inward office of exchange\",\"status\":\"\",\"time\":\"2018-11-09 12:34:37\",\"timeZone\":\"\"},{\"desc\":\"[RU,620966]Processing,Arrival at inward office of exchange\",\"status\":\"\",\"time\":\"2018-11-08 17:03:45\",\"timeZone\":\"\"},{\"desc\":\"[RU,620984]Processing,Departure from transit office of exchange\",\"status\":\"\",\"time\":\"2018-11-08 13:30:07\",\"timeZone\":\"\"},{\"desc\":\"[RU,620980]Processing,Departure from inward office of exchange\",\"status\":\"\",\"time\":\"2018-11-08 13:30:07\",\"timeZone\":\"\"},{\"desc\":\"[叶卡捷琳堡]已完成寄达地清关\",\"status\":\"\",\"time\":\"2018-11-08 13:30:00\",\"timeZone\":\"\"},{\"desc\":\"[Ekaterinburg]Left the place of international exchange\",\"status\":\"\",\"time\":\"2018-11-08 11:30:07\",\"timeZone\":\"\"},{\"desc\":\"[RU,620981]Handed over to customs\",\"status\":\"\",\"time\":\"2018-11-08 03:48:57\",\"timeZone\":\"\"},{\"desc\":\"[叶卡捷琳堡]【叶卡捷琳堡】发送到境外海关部门\",\"status\":\"\",\"time\":\"2018-11-08 03:48:00\",\"timeZone\":\"\"},{\"desc\":\"[RU,620981]Import of international mail\",\"status\":\"\",\"time\":\"2018-11-08 01:06:48\",\"timeZone\":\"\"},{\"desc\":\"[叶卡捷琳堡]已到达寄达地\",\"status\":\"\",\"time\":\"2018-11-08 01:06:00\",\"timeZone\":\"\"},{\"desc\":\"[RU,620984]Processing,Arrived at the territory of Russian Federation\",\"status\":\"\",\"time\":\"2018-11-07 23:45:11\",\"timeZone\":\"\"},{\"desc\":\"[RU,620980]Processing,Arrival at inward office of exchange\",\"status\":\"\",\"time\":\"2018-11-07 23:45:11\",\"timeZone\":\"\"},{\"desc\":\"[Ekaterinburg]Import of international mail\",\"status\":\"\",\"time\":\"2018-11-07 23:06:48\",\"timeZone\":\"\"},{\"desc\":\"[伊犁州]已交航空公司运输\",\"status\":\"\",\"time\":\"2018-10-30 00:00:00\",\"timeZone\":\"\"},{\"desc\":\"[伊犁州]离开【伊犁邮件处理中心】，下一站【霍尔果斯】\",\"status\":\"\",\"time\":\"2018-10-25 13:48:58\",\"timeZone\":\"\"},{\"desc\":\"[伊犁州]到达【伊犁邮件处理中心】\",\"status\":\"\",\"time\":\"2018-10-25 10:32:04\",\"timeZone\":\"\"},{\"desc\":\"[乌鲁木齐]离开【乌市航站】，下一站【新疆维吾尔自治区伊宁邮区中心局邮件处理中心】\",\"status\":\"\",\"time\":\"2018-10-24 14:27:04\",\"timeZone\":\"\"},{\"desc\":\"[CN]Export of international mail\",\"status\":\"\",\"time\":\"2018-10-19 15:13:00\",\"timeZone\":\"\"},{\"desc\":\"[乌鲁木齐]【乌鲁木齐互换局】已进口互封\",\"status\":\"\",\"time\":\"2018-10-19 14:47:59\",\"timeZone\":\"\"},{\"desc\":\"[乌鲁木齐]【乌鲁木齐互换局】已出口开拆\",\"status\":\"\",\"time\":\"2018-10-19 14:24:31\",\"timeZone\":\"\"},{\"desc\":\"[乌鲁木齐]离开【乌市航站】，下一站【乌鲁木齐互换局】\",\"status\":\"\",\"time\":\"2018-10-18 12:17:02\",\"timeZone\":\"\"},{\"desc\":\"[乌鲁木齐]到达【乌市航站】\",\"status\":\"\",\"time\":\"2018-10-18 12:12:22\",\"timeZone\":\"\"},{\"desc\":\"[乌鲁木齐]离开【邮政乌市小包业务营销】，下一站【乌鲁木齐国际】\",\"status\":\"\",\"time\":\"2018-10-17 19:14:22\",\"timeZone\":\"\"},{\"desc\":\"[乌鲁木齐]离开【邮政乌市小包业务营销】，下一站【乌市航站】\",\"status\":\"\",\"time\":\"2018-10-17 19:14:00\",\"timeZone\":\"\"},{\"desc\":\"[wulumuqi]【邮政乌市小包业务营销】已收寄\",\"status\":\"\",\"time\":\"2018-10-14 10:17:04\",\"timeZone\":\"\"},{\"desc\":\"[CN]Acceptance\",\"status\":\"\",\"time\":\"2018-10-14 10:17:00\",\"timeZone\":\"\"}],\"url\":\"https://www.pochta.ru/\"},\"shippingTime\":30.0,\"showEstimateTime\":false,\"status\":\"SIGNIN\",\"statusDesc\":\"Your parcel has been successfully delivered.\",\"success\":true,\"syncQuery\":false}],\"success\":true,\"timeSeconds\":0.018}"
       println(parser.replace(text))


    }

}