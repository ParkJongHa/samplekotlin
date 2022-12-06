package com.example.demo.sample004_cache

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Service
class Sample004Service {

    @Cacheable(value=["sample004_cache"], key = "#cacheKey")
    fun getCacheValue(cacheKey: Int): String {
        return "Sample004Service - Server return time : ${LocalDateTime.now()}, cacheKey:$cacheKey"
    }

    /**
     * Don't forget to add @EnableScheduling in @SpringBootApplication class
     */
    @CacheEvict(value=["sample004_cache"], allEntries = true)
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    fun evictCacheValue() {
        println("println evictCacheValue")
    }

}

