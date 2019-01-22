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
import tk.epavlov.microsystem.boot.parsers.cainiao.CainiaoConfig
import tk.epavlov.microsystem.boot.parsers.cainiao.CainiaoParser

@RunWith(SpringRunner::class)
@EnableConfigurationProperties
@ContextConfiguration(classes = [Application::class])
@SpringBootTest
@TestPropertySource(locations = ["classpath:application.yml"],
        properties = ["logging.level.tk.epavlov.microsystem:DEBUG"])

class CainiaoTest : CommonInterface {
    @Autowired
    lateinit var parser: CainiaoParser
    @Autowired
    lateinit var config: CainiaoConfig


    val track = "RB672036540SG"//"RP052760925CN"
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
}