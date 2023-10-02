package com.zzys.railway.framework.starter.idempotent.core.handler.token.controller;

import com.zzys.railway.framework.starter.convention.result.Result;
import com.zzys.railway.framework.starter.idempotent.core.handler.token.IdempotentTokenService;
import com.zzys.railway.framework.starter.web.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于 Token 验证请求幂等性控制器
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:52
 */
@RestController
@RequiredArgsConstructor
public class IdempotentTokenController {

    private final IdempotentTokenService idempotentTokenService;

    /**
     * 请求申请Token
     */
    @GetMapping("/token")
    public Result<String> createToken() {
        return Results.success(idempotentTokenService.createToken());
    }
}
