package com.zzys.railway.framework.starter.idempotent.config;

import com.zzys.railway.framework.starter.cache.config.RedisDistributedProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * 幂等元数据
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:56
 */
@Data
@ConfigurationProperties(prefix = IdempotentProperties.PREFIX)
public class IdempotentProperties {

    public static final String PREFIX = "congomall.idempotent.token";

    /**
     * Token 幂等 Key 前缀
     */
    private String prefix;

    /**
     * Token 申请后过期时间
     * 单位默认毫秒 {@link TimeUnit#MILLISECONDS}
     * 随着分布式缓存过期时间单位 {@link RedisDistributedProperties#valueTimeUnit} 而变化
     */
    private Long timeout;
}
