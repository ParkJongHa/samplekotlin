package sample04_spring.demo022_jpa

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo022UserController(
    val demo008UserService: Demo022UserService
) {

    @GetMapping("/api/demo021/user/{id}")
    fun getUser(@PathVariable id: Long): User? {
        return demo008UserService.findById(id)
    }

}