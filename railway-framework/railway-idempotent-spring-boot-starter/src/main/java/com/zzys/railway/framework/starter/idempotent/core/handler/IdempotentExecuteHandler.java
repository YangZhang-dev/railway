package com.zzys.railway.framework.starter.idempotent.core.handler;

import com.zzys.railway.framework.starter.idempotent.annotation.Idempotent;
import com.zzys.railway.framework.starter.idempotent.core.wrapper.IdempotentParamWrapper;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 幂等执行处理器
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:33
 */
public interface IdempotentExecuteHandler {

    /**
     * 幂等处理逻辑
     *
     * @param wrapper 幂等参数包装器
     */
    void handler(IdempotentParamWrapper wrapper);

    /**
     * 执行幂等处理逻辑
     *
     * @param joinPoint  AOP 方法处理
     * @param idempotent 幂等注解
     */
    void execute(ProceedingJoinPoint joinPoint, Idempotent idempotent);

    /**
     * 异常流程处理
     */
    default void exceptionProcessing() {

    }

    /**
     * 后置处理
     */
    default void postProcessing() {

    }
}
