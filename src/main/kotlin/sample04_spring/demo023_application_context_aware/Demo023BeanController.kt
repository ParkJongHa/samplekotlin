package sample04_spring.demo023_application_context_aware

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo023BeanController {

    @GetMapping("/api/demo023/{beanToString}")
    fun getUser(@PathVariable beanToString: String): String {
        return AppBeanUtil.getBean(beanToString).toString()
    }

}