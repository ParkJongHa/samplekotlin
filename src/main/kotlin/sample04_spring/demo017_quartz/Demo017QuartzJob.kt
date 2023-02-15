package sample04_spring.demo017_quartz

import org.quartz.JobExecutionContext
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.quartz.QuartzJobBean

class Demo017QuartzJob: QuartzJobBean() {

    private var demo017QuartzService: Demo017QuartzService? = null

//    private val log: Logger = LoggerFactory.getLogger(Demo005QuartzJob::class.java) // logger

    override fun executeInternal(context: JobExecutionContext) {
        if (null == demo017QuartzService) {
            val applicationContext: ApplicationContext? = context
                .jobDetail
                ?.jobDataMap
                ?.get(ApplicationContext::class.simpleName) as ApplicationContext?

            demo017QuartzService = applicationContext?.getBean(Demo017QuartzService::class.java)
        }

        demo017QuartzService?.execute()
    }

}