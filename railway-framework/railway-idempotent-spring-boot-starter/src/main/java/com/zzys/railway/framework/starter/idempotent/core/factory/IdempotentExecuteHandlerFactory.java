package com.zzys.railway.framework.starter.idempotent.core.factory;

import com.zzys.railway.framework.starter.base.ApplicationContextHolder;
import com.zzys.railway.framework.starter.idempotent.core.handler.IdempotentExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.handler.param.IdempotentParamByRestAPIExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.handler.param.IdempotentParamService;
import com.zzys.railway.framework.starter.idempotent.core.handler.spel.IdempotentSpELByMQExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.handler.spel.IdempotentSpELByRestAPIExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.handler.token.IdempotentTokenByRestAPIExecuteHandler;
import com.zzys.railway.framework.starter.idempotent.core.handler.token.IdempotentTokenService;
import com.zzys.railway.framework.starter.idempotent.enums.IdempotentSceneEnum;
import com.zzys.railway.framework.starter.idempotent.enums.IdempotentTypeEnum;

/**
 * 幂等处理器简单工厂
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:39
 */
public final class IdempotentExecuteHandlerFactory {

    /**
     * 获取幂等执行处理器
     *
     * @param scene 指定幂等验证场景类型
     * @param type  指定幂等处理类型
     * @return 幂等执行处理器
     */
    public static IdempotentExecuteHandler getInstance(IdempotentSceneEnum scene, IdempotentTypeEnum type) {
        IdempotentExecuteHandler result = null;
        switch (scene) {
            case RESTAPI -> {
                switch (type) {
                    case PARAM -> result = ApplicationContextHolder.getBean(IdempotentParamByRestAPIExecuteHandler.class);
                    case TOKEN -> result = ApplicationContextHolder.getBean(IdempotentTokenByRestAPIExecuteHandler.class);
                    case SPEL -> result = ApplicationContextHolder.getBean(IdempotentSpELByRestAPIExecuteHandler.class);
                    default -> {
                    }
                }
            }
            case MQ -> result = ApplicationContextHolder.getBean(IdempotentSpELByMQExecuteHandler.class);
            default -> {
            }
        }
        return result;
    }
}
