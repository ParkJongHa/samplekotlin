package com.example.demo.sample004_cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class Sample004SpringCachingConfig {

    @Bean
    fun cacheManager(): CacheManager {
        return ConcurrentMapCacheManager("sample004_cache")
    }

}