package sample04_spring.demo016_quartz

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Demo016QuartzService {

    fun execute() {
        if (false) println("Demo015QuartzService execute time : ${LocalDateTime.now()}")
    }
}