package com.zzys.railway.framework.starter.cache.config;

import com.zzys.railway.framework.starter.cache.core.RedisCache;
import com.zzys.railway.framework.starter.cache.core.RedisKeySerializer;
import lombok.AllArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 缓存自动装配
 *
 * @author YangZhang
 * @createTime 2023/09/30/ 19:45
 */
@AllArgsConstructor
@Configuration
@EnableConfigurationProperties({RedisDistributedProperties.class, BloomFilterPenetrateProperties.class})
public class CacheAutoConfiguration {

    private final RedisDistributedProperties redisDistributedProperties;

    /**
     * 创建 Redis Key 序列化器，可自定义 Key Prefix
     */
    @Bean
    public RedisKeySerializer redisKeySerializer() {
        String prefix = redisDistributedProperties.getPrefix();
        String prefixCharset = redisDistributedProperties.getPrefixCharset();
        return new RedisKeySerializer(prefix, prefixCharset);
    }

    /**
     * 防止缓存穿透的布隆过滤器
     */
    @Bean
    @ConditionalOnProperty(prefix = BloomFilterPenetrateProperties.PREFIX, name = "enabled", havingValue = "true")
    public RBloomFilter<String> cachePenetrationBloomFilter(RedissonClient redissonClient, BloomFilterPenetrateProperties bloomFilterPenetrateProperties) {
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter(bloomFilterPenetrateProperties.getName());
        cachePenetrationBloomFilter.tryInit(bloomFilterPenetrateProperties.getExpectedInsertions(), bloomFilterPenetrateProperties.getFalseProbability());
        return cachePenetrationBloomFilter;
    }

    @Bean
    // 静态代理模式: Redis 客户端代理类增强
    public RedisCache stringRedisTemplateProxy(RedisKeySerializer redisKeySerializer,
                                               StringRedisTemplate stringRedisTemplate,
                                               RedissonClient redissonClient) {
        stringRedisTemplate.setKeySerializer(redisKeySerializer);
        return new RedisCache(stringRedisTemplate, redisDistributedProperties, redissonClient);
    }
}
