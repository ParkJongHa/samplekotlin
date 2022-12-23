package com.example.demo.sample008_jpa

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Sample008UserController(
    val sample008UserService: Sample008UserService
) {

    @GetMapping("/api/sample008/user/{id}")
    fun getUser(@PathVariable id: Long): User? {
        return sample008UserService.findById(id)
    }

}