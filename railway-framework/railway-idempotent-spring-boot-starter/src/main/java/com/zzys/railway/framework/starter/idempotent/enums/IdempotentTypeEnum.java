package com.zzys.railway.framework.starter.idempotent.enums;

/**
 * 幂等类型枚举
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:11
 */
public enum IdempotentTypeEnum {

    /**
     * 基于 Token 方式验证
     */
    TOKEN,

    /**
     * 基于方法参数方式验证
     */
    PARAM,

    /**
     * 基于 SpEL 表达式方式验证
     */
    SPEL
}
