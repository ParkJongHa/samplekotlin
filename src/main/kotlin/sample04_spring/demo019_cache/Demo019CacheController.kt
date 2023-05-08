package sample04_spring.demo019_cache

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
 */
@RestController
class Demo019CacheController {

    /**
     * http://localhost:8080/api/demo018-2?cacheIntKey=1&cacheStringKey=A
     */
    @Cacheable(
        cacheManager = "cache2Manager",
        value=["cache2Names"],
        key = "#cacheIntKey.toString().concat(#cacheStringKey)"
    )
    @GetMapping("/api/demo019")
    fun getCachedValue(
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
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS) // @Scheduled(cron = "0 0/30 * * * ?")
    fun evictCachedValue() {
        println("Demo018Cache2Controller evictCache2Value ${LocalDateTime.now()}")
    }

}