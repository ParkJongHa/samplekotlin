package com.example.demo.sample004_cache

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Sample004Controller(
    val sample004Service: Sample004Service
) {

    /**
     * http://localhost:8080/api/sample004?cacheKey=1
     */
    @GetMapping("/api/sample004")
    fun getCacheValue(@RequestParam("cacheKey") cacheKey: Int): String {
        return sample004Service.getCacheValue(cacheKey)
    }

}

