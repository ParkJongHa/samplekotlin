package sample01_kotlin.demo004_coroutines.ex001

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main1() = runBlocking { // this: CoroutineScope
//    launch { // launch a new coroutine and continue // launch is a coroutine builder
//        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
//        println("World!") // print after delay
//    }

    launch{doWorld()}

    println("Hello") // main coroutine continues while a previous one is delayed
}

suspend fun doWorld() {
    delay(3000L)
    println("World!")
}