package tk.epavlov.microsystem.boot.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tk.epavlov.microsystem.boot.common.CommonInterface
import tk.epavlov.microsystem.boot.common.TrackData
import tk.epavlov.microsystem.boot.parsers.ParserService

@RestController
@RequestMapping(value = ["tracking"])
class ParserController : CommonInterface{

    @Autowired
    lateinit var parserService: ParserService

    @GetMapping
    fun getTimes(@RequestParam("trackId", required = true)  trackId: String): List<TrackData?> {
        return  parserService.getTrackInfo(trackId)
    }


}