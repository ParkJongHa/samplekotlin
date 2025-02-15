package sample01_kotlin.demo010_string.ex007_string_to_hex

fun main1() {
    val hex = "#FFAA7711"

    val a = hex.substring(1,3)
    val r = hex.substring(3,5)
    val g = hex.substring(5,7)
    val b = hex.substring(7,9)

    println(a + " " + a.toInt(16))
    println(r + " " + r.toInt(16))
    println(g + " " + g.toInt(16))
    println(b + " " + b.toInt(16))
}