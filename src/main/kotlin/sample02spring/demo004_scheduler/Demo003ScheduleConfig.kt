package sample02spring.demo004_scheduler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
import org.springframework.scheduling.support.CronTrigger

@Configuration
class Demo003ScheduleConfig(
    val demo003Service: sample02spring.demo004_scheduler.Demo003Service
) {

    @Bean
    fun taskScheduler(): TaskScheduler {
        val concurrentTaskScheduler = ConcurrentTaskScheduler()

        concurrentTaskScheduler.schedule({
            demo003Service.task()
        }, CronTrigger("0/10 * * * * *"))

        return concurrentTaskScheduler
    }

}