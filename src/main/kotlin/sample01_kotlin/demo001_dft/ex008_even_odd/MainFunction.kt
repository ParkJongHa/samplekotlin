package sample01_kotlin.demo001_dft.ex008_even_odd

fun main1() {
    val nTime = 100_000_000

    var delay = System.currentTimeMillis()
    for (i in 1..nTime) {isEvenWithAri(i)}
    println("isEvenWithAri delay x$nTime ${System.currentTimeMillis()-delay}")

    delay = System.currentTimeMillis()
    for (i in 1..nTime) {isEvenWithBit(i)}
    println("isEvenWithBit delay x$nTime ${System.currentTimeMillis()-delay}")
}

/**
x100,000,000 delay 32ms
 */
fun isEvenWithBit(num: Int): Boolean {
    return num.and(1) == 0
}

/**
x100,000,000 delay 35ms
 */
fun isEvenWithAri(num: Int): Boolean {
    return num % 2 == 0
}