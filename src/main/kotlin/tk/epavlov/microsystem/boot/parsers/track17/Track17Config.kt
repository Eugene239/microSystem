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

/**
 * todo change referer'Referer': 'http://www.17track.net/pt/track?nums={package}&fc=0',
 *
    "ret":1      - есть результат
    "msg":"Ok"   - статус запроса
    "dat"{
    "c":"RQ.SG"   - трек-номер
    "d":19131   - страна отправителя
    "e":11011   - страна получателя
    "f":1      - трек-номер найден (
    0:"Not Found",Title_0:"Not found, The courier hasn't been accepted this order yet",
    1:"Transporting",Title_1:"In transportation",
    2:"Pick Up",Title_2:"Item has been arrived in its destination country, Try to contact with local courier",
    3:"Delivered",Title_3:"Successfully delivered")
    "g":1      - статус трека со страны отправителя (
    0:"Unable To Track", Title_0:"Not recognize the tracking number or destination country doesn't support online tracking",
    1:"Normal Tracking",Title_1:"Normal tracking, the tracking result is available",
    2:"Not Found",Title_2:"Normal tracking, but the tracking result isn't available yet",
    3:"Web Error",Title_3:"Access to destination's server error, Maybe caused by temporarily disconnected server",
    4:"Process Error",Title_4:"Process error, Maybe caused by destination data format change, We will check it later.",
    5:"In Error (Use Cache)",Title_5:"Track error, Display the cache result maybe caused by destination data format change, We will check it later",
    6:"Server Busy",Title_6:"Destination's server prompt busy",
    7:"Server Busy (Use Cache)",Title_7:"Destination's server is busy, Display the cache result")
    "h":1      - статус трека со страны получателя (то же, что и выше)
    "i":-1      - сколько дней доставлялся трек, только для Delivered
    "j":0      - длительность запроса со страны отправителя
    "k":0      - длительность запроса со страны получателя
    "l":9      - язык треков со страны отправителя
    "m":29      - язык треков со страны получателя
    "v":"date"   - дата кэшированного запроса со страны отправителя
    "w":"date"   - дата кэшированного запроса со страны получателя
    "x":[      - массив треков со страны отправителя
    {"a":"2013-09-21 00:00","b":"Despatched to overseas (Country code: KZ)"}
    ]
    "y":[      - массив треков со страны получателя
    ]
    "z":{"a":"2013-10-16 18:01","b":"[289009] Участок по обработке международной почты "СЦ" г.Алматы, Регистрация на участке"}      - последний трек
    }
 */