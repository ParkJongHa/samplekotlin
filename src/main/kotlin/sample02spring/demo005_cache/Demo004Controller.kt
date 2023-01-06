package sample02spring.demo005_cache

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo004Controller(
    val demo004Service: sample02spring.demo005_cache.Demo004Service
) {

    /**
     * http://localhost:8080/api/demo004?cacheKey=1
     */
    @GetMapping("/api/demo004")
    fun getCacheValue(@RequestParam("cacheKey") cacheKey: Int): String {
        return demo004Service.getCacheValue(cacheKey)
    }

}

