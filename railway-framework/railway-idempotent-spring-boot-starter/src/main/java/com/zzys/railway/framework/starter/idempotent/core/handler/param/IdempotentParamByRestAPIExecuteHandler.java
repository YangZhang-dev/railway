package com.zzys.railway.framework.starter.idempotent.core.handler.param;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSON;
import com.zzys.railway.framework.starter.convention.exception.ClientException;
import com.zzys.railway.framework.starter.idempotent.core.aspect.IdempotentContext;
import com.zzys.railway.framework.starter.idempotent.core.handler.AbstractIdempotentExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.wrapper.IdempotentParamWrapper;
import com.zzys.railway.framework.starter.user.core.UserContext;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 基于参数验证请求幂等性，适用于 RestAPI 场景
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:50
 */
@RequiredArgsConstructor
public class IdempotentParamByRestAPIExecuteHandler extends AbstractIdempotentExecuteHandler implements IdempotentParamService {

    private final RedissonClient redissonClient;

    private final static String LOCK = "lock:param:restAPI";

    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {
        String lockKey = String.format("idempotent:path:%s:currentUserId:%s:md5:%s", getServletPath(), getCurrentUserId(), calcArgsMD5(joinPoint));
        return IdempotentParamWrapper.builder().lockKey(lockKey).joinPoint(joinPoint).build();
    }

    /**
     * @return 获取当前线程上下文 ServletPath
     */
    private String getServletPath() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest().getServletPath();
    }

    /**
     * @return 当前操作用户 ID
     */
    private String getCurrentUserId() {
        String userId = UserContext.getUserId();
        if(StrUtil.isBlank(userId)){
            throw new ClientException("用户ID获取失败，请登录");
        }
        return userId;
    }

    /**
     * @return joinPoint md5
     */
    private String calcArgsMD5(ProceedingJoinPoint joinPoint) {
        String md5 = DigestUtil.md5Hex(JSON.toJSONBytes(joinPoint.getArgs()));
        return md5;
    }

    @Override
    public void handler(IdempotentParamWrapper wrapper) {
        String lockKey = wrapper.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        if (!lock.tryLock()) {
            throw new ClientException(wrapper.getIdempotent().message());
        }
        IdempotentContext.put(LOCK, lock);
    }

    @Override
    public void postProcessing() {
        RLock lock = null;
        try {
            lock = (RLock) IdempotentContext.getKey(LOCK);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }
}
