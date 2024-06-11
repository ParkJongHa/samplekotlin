package sample01_kotlin.demo004_og

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class OgExtractorCache<T, U> {

    private val map = mutableMapOf<T,U>()
    private val mutex = Mutex() // Mutual exclusion for coroutines.

    fun clear() {map.clear()}
    fun isEmpty(): Boolean = map.isEmpty()
    fun isNotEmpty(): Boolean = !isEmpty()

    suspend fun get(key: T): U? = mutex.withLock {
        return map[key]
    }
    suspend fun set(key: T, value: U) = mutex.withLock {
        map[key] = value
    }
}

suspend fun <T, U> OgExtractorCache<T, U>.computeIfAbsent(
    key: T,
    valueProducer: suspend OgExtractorCache<T, U>.(T) -> U
): U {
    return get(key) ?: run {
        val value = valueProducer(key)
        set(key, value)
        value
    }
}