import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import tk.epavlov.microsystem.boot.Application
import tk.epavlov.microsystem.boot.parsers.track17.Track17Config
import tk.epavlov.microsystem.boot.parsers.track17.Track17Parser

@RunWith(SpringRunner::class)
@EnableConfigurationProperties
@ContextConfiguration(classes = [Application::class])
@SpringBootTest
@TestPropertySource(locations=["classpath:application.yaml"],
        properties = ["logging.level.tk.epavlov.microsystem:DEBUG"])

class Track17Test{
    @Autowired
    lateinit var parser : Track17Parser
    @Autowired
    lateinit var config: Track17Config

    val logger  = LoggerFactory.getLogger(this.javaClass)

    val track ="RP052760925CN"//"12312sdfwe324werfw"//"RP052760925CN"

    @Before
    fun setupLog(){
        logger.info(config.toString())
    }

    @Test
    fun get() {
        runBlocking {
            val track= parser.getTrack(track)
            print("\nfound track: $track\n\n")
        }
    }

}