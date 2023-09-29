package com.zzys.railway.framework.starter.distributedid.config;

import com.zzys.railway.framework.starter.distributedid.core.snowflake.chooseworkid.LocalRedisWorkIdChoose;
import com.zzys.railway.framework.starter.distributedid.core.snowflake.chooseworkid.RandomWorkIdChoose;
import com.zzys.railway.framework.starter.base.ApplicationContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 分布式id自动配置
 * @author YangZhang
 * @createTime 2023/09/29/ 18:30
 */
@Import(ApplicationContextHolder.class)
@Configuration
@EnableConfigurationProperties(DistributedIdAutoConfigurationProperties.class)
public class DistributedIdAutoConfiguration {

    /**
     * 本地 Redis 构建雪花 WorkId 选择器
     */
    @Bean
    @ConditionalOnProperty("spring.data.redis.host")
    public LocalRedisWorkIdChoose redisWorkIdChoose() {
        return new LocalRedisWorkIdChoose();
    }

    /**
     * 随机数构建雪花 WorkId 选择器。如果项目未使用 Redis，使用该选择器
     */
    @Bean
    @ConditionalOnMissingBean(LocalRedisWorkIdChoose.class)
    public RandomWorkIdChoose randomWorkIdChoose() {
        return new RandomWorkIdChoose();
    }
}
