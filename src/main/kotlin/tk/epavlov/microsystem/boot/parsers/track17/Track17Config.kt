package tk.epavlov.microsystem.boot.parsers.track17

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@ConfigurationProperties(prefix = "parser.17track")
@PropertySource( "classpath:application.yaml")
@Configuration
open class Track17Config {
    var name: String=""
    var code: Int = 2
    var url: String=""
    var enabled: Boolean = false

    override fun toString(): String {
        return "17TrackConfig(name='$name', code=$code, url='$url', enabled=$enabled)"
    }


}