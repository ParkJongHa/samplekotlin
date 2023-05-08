package sample04_spring.demo018_cache

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo018Controller(
    val demo018Service: Demo018Service
) {

    /**
     * http://localhost:8080/api/demo017?cacheKey=1
     */
    @GetMapping("/api/demo018")
    fun getCacheValue(@RequestParam("cacheKey") cacheKey: Int): String {
        return demo018Service.getCacheValue(cacheKey)
    }

}

