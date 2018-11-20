package tk.epavlov.microsystem.boot.rest

import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.parsers.cainiao.CainiaoParser

@RestController
@RequestMapping("Cainiao")
class CainiaoController : CommonInterface {

    @Autowired
    lateinit var parser: CainiaoParser


    @GetMapping("/{trackId}")
    fun find(@PathVariable(name = "trackId") trackId: String): TrackData? {
        log.info(trackId)
        return runBlocking {
            parser.getTrack(trackId)
        }
    }
}