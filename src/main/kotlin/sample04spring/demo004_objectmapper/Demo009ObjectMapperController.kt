package sample04spring.demo004_objectmapper

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import sample04spring.demo008_jpa.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo009ObjectMapperController(
) {

    @GetMapping("/api/demo00119")
    fun getUser(): User {
        val anUser = ObjectMapper().convertValue("""{"id":122,"name":"Roy","age":22}""", User::class.java)
        return anUser
    }

    @GetMapping("/api/demo00119/list")
    fun getUserList(): List<User> {
        val listType = object : TypeReference<List<User>>() {}
        val userList = ObjectMapper().convertValue("""[{"id":122,"name":"Roy","age":22},{"id":123,"name":"Jone","age":23}]""", listType)
        return userList
    }

}