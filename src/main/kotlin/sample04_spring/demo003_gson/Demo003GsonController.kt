package sample04_spring.demo003_gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.UserVo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo003GsonController(
) {

    @GetMapping("/api/demo003")
    fun getUser(): UserVo {
        val userVo: UserVo = Gson().fromJson("""{"id":123,"name":"Jone","age":23}""", UserVo::class.java)
        return userVo
    }

    @GetMapping("/api/demo003/list")
    fun getUserList(): List<UserVo> {
        val listType = object : TypeToken<List<UserVo>>() {}.type
        val userVoList: List<UserVo> = Gson().fromJson("""[{"id":122,"name":"Roy","age":22},{"id":123,"name":"Jone","age":23}]""", listType)
        return userVoList
    }

}