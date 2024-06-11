package sample01_kotlin.demo001_dft.ex006_string_substring

import kotlin.random.Random

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

fun main3() {
    val str = "abcdefghi"

    println(str.length) // 9
    println(str.substring(0, 4)) // abcd
}

fun main4() {
    val str = "\"abc\""

    if (str.startsWith("\"")
        && str.endsWith("\"")
        && 2 < str.length) {
        val str1 = str.substring(1)
        println(str1.substring(0, str1.length-1))
    } else {
        println(str)
    }
}

fun main5() {
//    val queryString = "search?q=%EC%B9%B4%EB%A0%88&client=ms-android-samsung-ss&hl=ko&biw=412&bih=866&tbm=bks&source=lnms&sa=X&ved=0ahUKEwjO-MSHpMeAAxXvilYBHTptCVMQ0pQJCCk"
    val queryString = "search?q"
    val idx: Int = queryString.indexOf("=")
    println("idx : $idx")

    val key = queryString.substring(0, idx)
    val value = queryString.substring(idx + 1)
}

fun main6() {
    // yyyy-MM-dd HH:mm:ss
    val time = "20230814070726275180"
    val yyyy = time.substring(0, 4)
    val MM = time.substring(4, 6)
    val dd = time.substring(6, 8)
    val HH = time.substring(8, 10)
    val mm = time.substring(10, 12)
    val ss = time.substring(12, 14)

    println( "$yyyy-$MM-$dd $HH:$mm:$ss")
}

fun main7() {
    val rawPath = "\"/cube/web/naver/htt\"ps:/%2Fm.naver.com\""

    val path = rawPath.substringBeforeLast("\"", "")

    println( path ) // jpg
}

fun main() {
    for (i in 1..100_000_000) {
        val a = Integer.toHexString(Random.nextInt(256)) // alpha 투명도
        val r = Integer.toHexString(Random.nextInt(256))
        val g = Integer.toHexString(Random.nextInt(256))
        val b = Integer.toHexString(Random.nextInt(256))

        val argb = "" + // 3caf9c87
            (if (1==a.length) "0$a" else a) +
            (if (1==r.length) "0$r" else r) +
            (if (1==g.length) "0$g" else g) +
            (if (1==b.length) "0$b" else b)

        if (8 != argb.length) {
            println(argb + " " + argb.length)
        }
    }
}