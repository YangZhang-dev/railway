package com.zzys.railway.framework.starter.idempotent.core.handler.token;

import com.zzys.railway.framework.starter.idempotent.core.handler.IdempotentExecuteHandler;

/**
 * Token 幂等处理器
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:42
 */
public interface IdempotentTokenService extends IdempotentExecuteHandler {

    /**
     * 创建幂等验证Token
     */
    String createToken();
}
