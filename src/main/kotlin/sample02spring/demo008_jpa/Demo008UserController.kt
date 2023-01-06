package sample02spring.demo008_jpa

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo008UserController(
    val demo008UserService: Demo008UserService
) {

    @GetMapping("/api/demo008/user/{id}")
    fun getUser(@PathVariable id: Long): User? {
        return demo008UserService.findById(id)
    }

}