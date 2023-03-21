package sample04_spring.demo011_filter

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun demo011ReqResLogFilterBean(): FilterRegistrationBean<Demo011ReqResLogFilter> {
        val registration = FilterRegistrationBean<Demo011ReqResLogFilter>()
        registration.filter = Demo011ReqResLogFilter()
        registration.order = 1
        registration.addUrlPatterns("/*")

        return registration
    }

}