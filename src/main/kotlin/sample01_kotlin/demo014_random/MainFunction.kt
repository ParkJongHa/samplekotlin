package sample01_kotlin.demo014_random

import kotlin.random.Random

fun main() {
    for (i in 1..100) {
        println( Random.nextInt(7) )
    }
}