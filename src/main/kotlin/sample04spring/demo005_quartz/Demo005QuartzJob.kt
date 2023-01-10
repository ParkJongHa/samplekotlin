package sample04spring.demo005_quartz

import org.quartz.JobExecutionContext
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.quartz.QuartzJobBean

class Demo005QuartzJob: QuartzJobBean() {

    private var demo005QuartzService: Demo005QuartzService? = null

//    private val log: Logger = LoggerFactory.getLogger(Demo005QuartzJob::class.java) // logger

    override fun executeInternal(context: JobExecutionContext) {
        if (null == demo005QuartzService) {
            val applicationContext: ApplicationContext? = context
                .jobDetail
                ?.jobDataMap
                ?.get(ApplicationContext::class.simpleName) as ApplicationContext?

            demo005QuartzService = applicationContext?.getBean(Demo005QuartzService::class.java)
        }

        demo005QuartzService?.execute()
    }

}