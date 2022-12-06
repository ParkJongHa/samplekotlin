package com.example.demo.sample005_quartz

import jakarta.annotation.PostConstruct
import org.quartz.*
import org.quartz.impl.StdSchedulerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class Sample005Quartz {

    @Autowired
    lateinit var applicationContext: ApplicationContext

    private lateinit var schedulerFactory: SchedulerFactory
    private lateinit var scheduler: Scheduler

//    private val log: Logger = LoggerFactory.getLogger(Sample005Quartz::class.java)

    @PostConstruct
    fun start()  {
        schedulerFactory = StdSchedulerFactory()
        scheduler = schedulerFactory.scheduler
        scheduler.start()

        try {
            val jobDataMap = JobDataMap().apply {
                    put(ApplicationContext::class.simpleName, applicationContext)
                }

            val jobDetail = JobBuilder.newJob().ofType(Sample005QuartzJob::class.java)
                .setJobData(jobDataMap)
                .withIdentity(Sample005QuartzJob::class.simpleName+"Identity")
                .build()

            val trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build()

            scheduler.scheduleJob(jobDetail, trigger)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}