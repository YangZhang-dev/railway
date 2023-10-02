package com.zzys.railway.framework.starter.idempotent.enums;

/**
 * 校验场景枚举
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:11
 */
public enum IdempotentSceneEnum {

    /**
     * 基于 RestAPI 场景验证
     */
    RESTAPI,

    /**
     * 基于 MQ 场景验证
     */
    MQ
}
