package sample04_spring.demo007_restful

import data.UserDto
import org.springframework.web.bind.annotation.*

/*
REST (Representational State Transfer)
HTTP URI + HTTP Method(GET, POST, PUT, DELETE)
    HTTP URI 로 제어할 리소스 명시 후,
    HTTP Method 를 통해 해당 리소스를 제어하는 아키텍쳐
HTTP 프로토콜에 정의된 POST, GET, PUT, DELETE 가 각각 CRUD 오퍼레이션 정의
 */
@RestController
class Demo007RestController {

    /*
    http://localhost:8080/api/user/1
     */
    @GetMapping("/api/user/{id}")
    fun getUser(@PathVariable id:Long): UserDto {
        println("Demo007RestController getUser $id")
        return UserDto(id, "Joe$id", (20L+id).toInt())
    }

    /*
    http://localhost:8080/api/user?page=1
     */
    @GetMapping("/api/user")
    fun getUserList(@RequestParam(name = "page") page: Int): List<UserDto> {
        println("Demo007RestController getUserList $page")
        return listOf(
            UserDto(1, "Joe1", 21),
            UserDto(2, "Joe2", 22),
            UserDto(3, "Joe3", 23),
            UserDto(4, "Joe4", 24),
            UserDto(5, "Joe5", 25),
        )
    }

    /*
    use postman (https://www.postman.com)
    http://localhost:8080/api/user
    body raw json
        {"id":1,"name":"Joe1","age":21}
     */
    @PostMapping("/api/user")
    fun postUser(@RequestBody userDto: UserDto) {
        println("Demo007RestController postUser $userDto")
    }

    /*
    use postman (https://www.postman.com)
    http://localhost:8080/api/user
    body raw json
        {"id":1,"name":"Joe1","age":21}
     */
    @PutMapping("/api/user")
    fun putUser(@RequestBody userDto: UserDto) {
        println("Demo007RestController putUser $userDto")
    }

    /*
    http://localhost:8080/api/user?id=1
     */
    @DeleteMapping("/api/user")
    fun deleteUser(@RequestParam(name = "id") id: Long) {
        println("Demo007RestController deleteUser $id")
    }

}