package com.example.demo.sample010_gson

import com.example.demo.sample008_jpa.User
import com.google.gson.Gson
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Sample010GsonController(
) {

    @GetMapping("/api/sample010")
    fun getUser(): User {
        return Gson().fromJson("""{"id":123,"name":"Jone","age":23}""", User::class.java)
    }

}