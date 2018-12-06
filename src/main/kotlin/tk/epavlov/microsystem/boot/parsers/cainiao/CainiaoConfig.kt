package tk.epavlov.microsystem.boot.parsers.cainiao

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource


@ConfigurationProperties(prefix = "parser.cainiao")
@PropertySource( "classpath:application.yml")
@Configuration
open class CainiaoConfig {
    var name: String=""
    var code: Int = 0
    var url: String=""
    var enabled: Boolean = false

    override fun toString(): String {
        return "CainiaoConfig(name='$name', code=$code, url='$url', enabled=$enabled)"
    }

}