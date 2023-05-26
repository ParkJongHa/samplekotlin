package sample04_spring.demo026_logger

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

// Simple Logging Facade 4 Java
@RestController
class Demo026Slf4jController {

    @GetMapping("/api/demo026/")
    fun serverLog(@PathVariable id: Long): String {
        val aLog = LocalDateTime.now().toString()

        log().info("aLog = $aLog")

        return aLog
    }
}