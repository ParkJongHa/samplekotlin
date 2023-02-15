package sample01_kotlin.demo001_dft.ex006_string_substring

fun main1() {
    val str = "abc\uD83D\uDE33一二三!@#123일이삼ａｂｃしごと"

    println(str.subSequence(0, 4)) // abc?
    println(str.substring(0, 4)) // abc?
}

/**
 * get file extension
 */
fun main2() {
    val fileName = "cat.image.jpg"

    val extension = fileName.substringAfterLast(".", "")

    println(extension) // jpg
}
