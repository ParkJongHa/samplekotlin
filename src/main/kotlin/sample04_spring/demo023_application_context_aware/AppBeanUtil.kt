package sample04_spring.demo023_application_context_aware

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class AppBeanUtil : ApplicationContextAware {

    companion object {
        private var appContext: ApplicationContext? = null

        fun get(): ApplicationContext {
            return appContext!!
        }

        /**
         * beanName ex) demo023BeanController
         * */
        fun getBean(beanName: String): Any {
            return appContext!!.getBean(beanName) // appContext!!.getBean(Demo023BeanController::class.java))
        }
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        if (appContext == null) {
            appContext = applicationContext
        }
    }



}