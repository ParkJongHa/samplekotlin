package com.example.demo.sample009_gson

import com.example.demo.sample008_jpa.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Sample009GsonController(
) {

    @GetMapping("/api/sample009")
    fun getUser(): User {
        return Gson().fromJson("""{"id":123,"name":"Jone","age":23}""", User::class.java)
    }

    @GetMapping("/api/sample009/list")
    fun getUserList(): List<User> {
        val listType = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson("""[{"id":122,"name":"Roy","age":22},{"id":123,"name":"Jone","age":23}]""", listType)
    }

}