package sample04_spring.demo002_objectmapper

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import data.UserVo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo002ObjectMapperController(
) {

    @GetMapping("/api/demo002")
    fun getUser(): UserVo {
        val anUserVo: UserVo = ObjectMapper().convertValue("""{"id":122,"name":"Roy","age":22}""", UserVo::class.java)
        return anUserVo
    }

    @GetMapping("/api/demo002/list")
    fun getUserList(): List<UserVo> {
        val listType = object : TypeReference<List<UserVo>>() {}
        val userVoList: List<UserVo> = ObjectMapper().convertValue("""[{"id":122,"name":"Roy","age":22},{"id":123,"name":"Jone","age":23}]""", listType)
        return userVoList
    }

}