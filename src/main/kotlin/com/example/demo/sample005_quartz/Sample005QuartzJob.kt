package com.example.demo.sample005_quartz

import org.quartz.JobExecutionContext
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.quartz.QuartzJobBean

class Sample005QuartzJob: QuartzJobBean() {

    private var sample005QuartzService: Sample005QuartzService? = null

//    private val log: Logger = LoggerFactory.getLogger(Sample005QuartzJob::class.java) // logger

    override fun executeInternal(context: JobExecutionContext) {
        if (null == sample005QuartzService) {
            val applicationContext: ApplicationContext? = context
                .jobDetail
                ?.jobDataMap
                ?.get(ApplicationContext::class.simpleName) as ApplicationContext?

            sample005QuartzService = applicationContext?.getBean(Sample005QuartzService::class.java)
        }

        sample005QuartzService?.execute()
    }

}