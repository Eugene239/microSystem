package tk.epavlov.microsystem.boot.parsers.cdek

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource


@ConfigurationProperties(prefix = "parser.cdek")
@PropertySource("classpath:application.yml")
@Configuration
class CdekConfig {
    var name: String = ""
    var code: Int = 0
    var url: String = ""
    var enabled: Boolean = false
    var cookieUrl: String =""

    override fun toString(): String {
        return "CdekConfig(name='$name', code=$code, url='$url', enabled=$enabled, cookieUrl='$cookieUrl')"
    }


}