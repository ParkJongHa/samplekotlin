package sample04_spring.demo018_cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@EnableCaching
class Demo018SpringCachingConfig {

    @Primary
    @Bean
    fun cacheManager(): CacheManager {
        return ConcurrentMapCacheManager("demo018_cache")
    }

}