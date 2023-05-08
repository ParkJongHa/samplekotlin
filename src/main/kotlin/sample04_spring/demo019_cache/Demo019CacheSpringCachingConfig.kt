package sample04_spring.demo019_cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class Demo019CacheSpringCachingConfig {

    @Bean
    fun cache2Manager(): CacheManager {
        return ConcurrentMapCacheManager("cache2Names")
    }

}