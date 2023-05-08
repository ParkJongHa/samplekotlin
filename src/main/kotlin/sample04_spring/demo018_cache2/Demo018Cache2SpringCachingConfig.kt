package sample04_spring.demo018_cache2

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class Demo018Cache2SpringCachingConfig {


    @Bean
    fun cache2Manager(): CacheManager {
        return ConcurrentMapCacheManager("cache2Names")
    }

}