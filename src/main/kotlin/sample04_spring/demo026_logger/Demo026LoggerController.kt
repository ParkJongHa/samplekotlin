package sample04_spring.demo026_logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class Demo026LoggerController {

    val log: Logger = LoggerFactory.getLogger(Demo026LoggerController::class.java)

    @GetMapping("/api/demo026")
    fun serverLog(@PathVariable id: Long): String {
        val aLog = LocalDateTime.now().toString()
        log.info("serverLog = $aLog")

        return aLog
    }
}