package sample04_spring.demo017_quartz

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Demo017QuartzService {

    fun execute() {
        if (false) println("Demo015QuartzService execute time : ${LocalDateTime.now()}")
    }
}