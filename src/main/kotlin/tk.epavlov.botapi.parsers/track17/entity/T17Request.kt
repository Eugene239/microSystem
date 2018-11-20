//package tk.epavlov.botapi.parsers.track17.entity
//
//import com.epavlov.common.botapi.utils.gson
//
//class T17Request (val guid:String?="", val data: List<T17Num>, val fc:String?="0", val sc:String?="0")
//class T17Num(val num:String)
//
//fun main(args: Array<String>) {
//    val request= T17Request("", listOf(T17Num("1111"), T17Num("22222")))
//    println(gson.toJson(request))
//}