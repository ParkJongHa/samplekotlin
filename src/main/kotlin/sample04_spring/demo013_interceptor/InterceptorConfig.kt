package sample04_spring.demo013_interceptor

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig{

    @Bean
    fun webMvcConfigurer(): WebMvcConfigurer {
        return object: WebMvcConfigurer {
            override fun addInterceptors(registry: InterceptorRegistry) {
                registry.addInterceptor(Demo013ReqResLogInterceptor())
                    .order(1)
                    .addPathPatterns("/**")
            }
        }
    }

}