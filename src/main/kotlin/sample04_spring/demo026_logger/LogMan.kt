package sample04_spring.demo026_logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory
inline fun <reified T> T.log(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}
