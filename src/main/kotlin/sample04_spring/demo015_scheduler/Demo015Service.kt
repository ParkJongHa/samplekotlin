package sample04_spring.demo015_scheduler

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Demo015Service {

    fun task() {
        if (false) println("Demo016Service task exe time : ${LocalDateTime.now()}")
    }

}