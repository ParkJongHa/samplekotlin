package sample04_spring.demo023_jpa

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo023UserController(
    val demo023UserService: Demo023UserService
) {

    @GetMapping("/api/demo021/user/{id}")
    fun getUser(@PathVariable id: Long): User? {
        return demo023UserService.findById(id)
    }

}