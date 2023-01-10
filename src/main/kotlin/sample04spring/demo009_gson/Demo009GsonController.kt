package sample04spring.demo009_gson

import sample04spring.demo008_jpa.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo009GsonController(
) {

    @GetMapping("/api/demo009")
    fun getUser(): User {
        return Gson().fromJson("""{"id":123,"name":"Jone","age":23}""", User::class.java)
    }

    @GetMapping("/api/demo009/list")
    fun getUserList(): List<User> {
        val listType = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson("""[{"id":122,"name":"Roy","age":22},{"id":123,"name":"Jone","age":23}]""", listType)
    }

}