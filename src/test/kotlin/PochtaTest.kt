import kotlinx.coroutines.runBlocking
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
import tk.epavlov.microsystem.boot.parsers.pochta.PochtaConfig
import tk.epavlov.microsystem.boot.parsers.pochta.PochtaParser
import java.util.*

@RunWith(SpringRunner::class)
@EnableConfigurationProperties
@ContextConfiguration(classes = [Application::class])
@SpringBootTest
@TestPropertySource(locations=["classpath:application.yml"],
        properties = ["logging.level.tk.epavlov.microsystem:DEBUG"])

class PochtaTest : CommonInterface{
    @Autowired
    lateinit var parser : PochtaParser
    @Autowired
    lateinit var config: PochtaConfig


    val track ="RB672036540SG"//"12312sdfwe324werfw"//"RP052760925CN"
    val errorTrack = UUID.randomUUID().toString().replace("-","")
    @Before
    fun setupLog(){
        log.info(config.toString())
    }

    @Test
    fun get() {
        runBlocking {
            val track= parser.getTrack(track)
            log.info("found track: $track")
        }
    }
    @Test
    fun errorTrack() {
        runBlocking {
            val track= parser.getTrack(errorTrack)
            log.info("found track: $track")
            assert(track ==null)
        }
    }

}