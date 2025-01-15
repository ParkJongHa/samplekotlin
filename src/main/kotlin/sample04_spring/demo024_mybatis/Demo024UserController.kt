package sample04_spring.demo024_mybatis

import data.UserVo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo024UserController(
    val demo024UserService: Demo024UserService
) {

    @GetMapping("/api/demo024/user/list")
    fun getUserList(): List<UserVo> {
        return demo024UserService.findAll()
    }

}