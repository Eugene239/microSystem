package tk.epavlov.microsystem.boot.parsers.pochta

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@ConfigurationProperties(prefix = "parser.pochta")
@PropertySource( "classpath:application.yml")
@Configuration
open class PochtaConfig {
    var name: String=""
    var code: Int = 1
    var url: String=""
    var enabled: Boolean = false

    override fun toString(): String {
        return "PochtaConfig(name='$name', code=$code, url='$url', enabled=$enabled)"
    }


}