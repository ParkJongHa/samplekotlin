package sample04_spring.demo024_mybatis

import data.UserGroupByVo
import data.UserVo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo024UserController(
    val demo024UserService: Demo024UserService
) {

    @GetMapping("/api/demo024/user/list")
    fun getUserList(): List<UserVo> {
        return demo024UserService.findAll()
    }

    @GetMapping("/api/demo024/user/group-by/{groupby}")
    fun getUserGroupBy(
        @PathVariable groupby: String
    ): List<UserGroupByVo> {
        return demo024UserService.findGroupBy(groupby)
    }

    @PostMapping("/api/demo024/user/list")
    fun postUserList() {
        return demo024UserService.insertUserList()
    }

    @GetMapping("/api/demo024/user/transactional/{isTransaction}/{isError}")
    fun getUserTransactional(
        @PathVariable isTransaction: Boolean,
        @PathVariable isError: Boolean,
    ) {
        return demo024UserService.insertUser(isTransaction, isError)
    }

}