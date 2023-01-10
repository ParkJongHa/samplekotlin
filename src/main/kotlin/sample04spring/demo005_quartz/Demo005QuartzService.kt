package sample04spring.demo005_quartz

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Demo005QuartzService {

    fun execute() {
        if (false) println("Demo005QuartzService execute time : ${LocalDateTime.now()}")
    }
}