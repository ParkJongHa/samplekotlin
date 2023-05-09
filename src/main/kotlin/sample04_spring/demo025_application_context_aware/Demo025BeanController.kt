package sample04_spring.demo025_application_context_aware

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo025BeanController {

    @GetMapping("/api/demo025/{beanToString}")
    fun getUser(@PathVariable beanToString: String): String {
        return AppBeanUtil.getBean(beanToString).toString()
    }

}