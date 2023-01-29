package sample01_kotlin.demo001_dft.ex006_string_substring

fun main1() {
    val str = "abc\uD83D\uDE33一二三!@#123일이삼ａｂｃしごと"

    println(str.subSequence(0, 4))
    println(str.substring(0, 4))
}
