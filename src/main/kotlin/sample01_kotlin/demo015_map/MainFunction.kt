package sample01_kotlin.demo015_map

fun main1() {
    val map = mutableMapOf<Int, String>()

    map[1] = "1"

    println( map[4] ) // null
    println( "-----------------------" )
}
