package tk.epavlov.microsystem.boot.common

import com.google.gson.Gson
import retrofit2.Response


fun <T>  Response<T>.info():String{
    println()
    println("message: "+ this.message())
    println("body: "+ Gson().toJson(this.body()))
    this.headers().toMultimap().forEach { t, u ->
        println("$t: $u")
    }
    return  ""
}