package sample04_spring.demo027_reqreswrapper

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterReqResConfig {

    @Bean
    fun requestResponseWrapperFilterBean(): FilterRegistrationBean<RequestResponseWrapperFilter> {
        val registration: FilterRegistrationBean<RequestResponseWrapperFilter> = FilterRegistrationBean()
        registration.filter = RequestResponseWrapperFilter()
        registration.addUrlPatterns("/api/demo027/*")
        registration.order = 2
        return registration
    }

}