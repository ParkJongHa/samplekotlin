package sample04_spring.demo027_reqreswrapper

import data.UserDto
import org.springframework.web.bind.annotation.*

@RestController
class Demo027UserController {

    @GetMapping("/api/demo027/{userGroup}")
    fun get(
        @PathVariable userGroup: String,
        @RequestParam(name = "pageNo") pageNo: Int,
        @RequestParam(name = "pageSize") pageSize: Int,
    ): String {
        return "userGroup:$userGroup, pageNo:$pageNo, pageSize:$pageSize"
    }

    @PostMapping("/api/demo027/{userId}")
    fun post(
        @PathVariable userId: Long,
        @RequestBody userDto: UserDto
    ): UserDto {
        val returnUser = UserDto().apply {
            id = userDto.id!! + userDto.id!!
            name = userDto.name + userDto.name
            age = userDto.age!! + userDto.age!!
        }
        return returnUser
    }

}