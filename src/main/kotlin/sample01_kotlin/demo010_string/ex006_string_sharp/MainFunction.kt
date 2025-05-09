
package sample01_kotlin.demo010_string.ex006_string_sharp

const val sample = "https://eopla.net/magazines/22222#"
//const val sample = "https://eopla.net/magazines/22222##"
//const val sample = "https://eopla.net/magazines/222#22?page=43&size=30"

// 끝이 붙은 # 은 다 지운다.
fun removeLastSharp(string: String): String {
    var temp = string

    while (temp.endsWith("#") && temp.lastIndexOf("#") == temp.lastIndex) {
        temp = temp.substring(0, temp.lastIndexOf("#"))
    }

    return temp
}

fun main1() {
    println( removeLastSharp(sample) )
}