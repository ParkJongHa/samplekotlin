package sample04_spring.demo017_cache

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Service
class Demo017Service {

    @Cacheable(value=["demo004_cache"], key = "#cacheKey")
    fun getCacheValue(cacheKey: Int): String {
        return "Demo004Service - Server return time : ${LocalDateTime.now()}, cacheKey:$cacheKey"
    }

    /**
     * Don't forget to add @EnableScheduling in @SpringBootApplication class
     */
    @CacheEvict(value=["demo004_cache"], allEntries = true)
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    fun evictCacheValue() {
        if (false) println("println evictCacheValue")
    }

}

