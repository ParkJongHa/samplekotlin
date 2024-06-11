package sample01_kotlin.demo002_rx

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.runBlocking
import kotlin.math.log


fun debounce(): Flow<Int> = flow<Int> {
    emit(1)
    emit(2)
    delay(500L)
    emit(3)
    emit(4)
    delay(200L)
    emit(5)
    delay(700L)
    emit(6)
}.debounce(400L)
//suspend fun main() {
//
//    debounce().collect { println(it.toString()) }
//
//}

fun sample(): Flow<Int> = flow {
    repeat(10) {
        emit(it)
        delay(1000)
    }
}.sample(2000)

fun main() = runBlocking<Unit> {
    sample().collect { println(it.toString()) }
}

