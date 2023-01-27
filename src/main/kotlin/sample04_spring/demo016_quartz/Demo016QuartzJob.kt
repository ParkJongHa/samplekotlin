package sample04_spring.demo016_quartz

import org.quartz.JobExecutionContext
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.quartz.QuartzJobBean

class Demo016QuartzJob: QuartzJobBean() {

    private var demo016QuartzService: Demo016QuartzService? = null

//    private val log: Logger = LoggerFactory.getLogger(Demo005QuartzJob::class.java) // logger

    override fun executeInternal(context: JobExecutionContext) {
        if (null == demo016QuartzService) {
            val applicationContext: ApplicationContext? = context
                .jobDetail
                ?.jobDataMap
                ?.get(ApplicationContext::class.simpleName) as ApplicationContext?

            demo016QuartzService = applicationContext?.getBean(Demo016QuartzService::class.java)
        }

        demo016QuartzService?.execute()
    }

}