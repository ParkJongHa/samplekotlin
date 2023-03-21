package sample04_spring.demo012_argument_resolver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import sample04_spring.demo013_interceptor.Demo013ReqResLogInterceptor

@Configuration
class ArgumentResolverConfig{

    @Autowired
    lateinit var webMvcConfigurer: WebMvcConfigurer

    @Bean
    fun webMvcConfigurerArgumentConfig(): WebMvcConfigurer {
//        webMvcConfigurer.addArgumentResolvers(UserHandlerMethodArgumentResolver())

        webMvcConfigurer

        return webMvcConfigurer
    }

}