package com.zzys.railway.framework.starter.idempotent.config;

import com.zzys.railway.framework.starter.cache.core.DistributedCache;
import com.zzys.railway.framework.starter.idempotent.core.aspect.IdempotentAspect;
import com.zzys.railway.framework.starter.idempotent.core.handler.param.IdempotentParamByRestAPIExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.handler.spel.IdempotentSpELByMQExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.handler.spel.IdempotentSpELByRestAPIExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.handler.spel.IdempotentSpELService;
import com.zzys.railway.framework.starter.idempotent.core.handler.token.IdempotentTokenByRestAPIExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.handler.token.IdempotentTokenService;
import com.zzys.railway.framework.starter.idempotent.core.handler.token.controller.IdempotentTokenController;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 幂等自动配置
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:59
 */
@Configuration
@EnableConfigurationProperties(IdempotentProperties.class)
public class IdempotentAutoConfiguration {

    /**
     * 幂等切面
     */
    @Bean
    public IdempotentAspect idempotentAspect() {
        return new IdempotentAspect();
    }

    /**
     * 参数方式幂等实现，基于 RestAPI 场景
     */
    @Bean
    @ConditionalOnMissingBean
    public IdempotentParamByRestAPIExecuteHandler idempotentParamExecuteHandler(RedissonClient redissonClient) {
        return new IdempotentParamByRestAPIExecuteHandler(redissonClient);
    }

    /**
     * Token 方式幂等实现，基于 RestAPI 场景
     */
    @Bean
    @ConditionalOnMissingBean
    public IdempotentTokenByRestAPIExecuteHandler idempotentTokenExecuteHandler(DistributedCache distributedCache,
                                                                                IdempotentProperties idempotentProperties) {
        return new IdempotentTokenByRestAPIExecuteHandler(distributedCache, idempotentProperties);
    }

    /**
     * 申请幂等 Token 控制器，基于 RestAPI 场景
     */
    @Bean
    public IdempotentTokenController idempotentTokenController(IdempotentTokenService idempotentTokenService) {
        return new IdempotentTokenController(idempotentTokenService);
    }

    /**
     * SpEL 方式幂等实现，基于 RestAPI 场景
     */
    @Bean
    @ConditionalOnMissingBean
    public IdempotentSpELService idempotentSpELByRestAPIExecuteHandler(RedissonClient redissonClient) {
        return new IdempotentSpELByRestAPIExecuteHandler(redissonClient);
    }

    /**
     * SpEL 方式幂等实现，基于 MQ 场景
     */
    @Bean
    @ConditionalOnMissingBean
    public IdempotentSpELByMQExecuteHandler idempotentSpELByMQExecuteHandler(DistributedCache distributedCache) {
        return new IdempotentSpELByMQExecuteHandler(distributedCache);
    }
}
