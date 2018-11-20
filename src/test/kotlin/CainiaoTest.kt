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

}