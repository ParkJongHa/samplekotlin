package sample04_spring.demo018_cache2

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

/**
 * 0. default cacheManager @Bean 에 @Primary 적용
 * 1. controller 에서 cache 적용
 * 2. multikey 적용
 * // 서버 기동시 최초 한번 실행
 * http://localhost:8080/api/demo018-2?cacheIntKey=1&cacheStringKey=A
 */
@RestController
class Demo018Cache2Controller {

    @Cacheable(
        cacheManager = "cache2Manager",
        value=["cache2Names"],
        key = "#cacheIntKey.toString().concat(#cacheStringKey)"
    )
    @GetMapping("/api/demo018-2")
    fun getCacheValue(
        @RequestParam("cacheIntKey") cacheIntKey: Int,
        @RequestParam("cacheStringKey") cacheStringKey: String
    ): String {
        return """Demo018Cache2Controller
            Server return time : ${LocalDateTime.now()}
            cacheIntKey : $cacheIntKey
            cacheStringKey : $cacheStringKey"""
    }

    @CacheEvict(
        cacheManager = "cache2Manager",
        value=["cache2Names"],
        allEntries = true
    )
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    fun evictCacheValue() {
        println("Demo018Cache2Controller evictCache2Value ${LocalDateTime.now()}")
    }

}