package com.zzys.railway.framework.starter.idempotent.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * MQ 消息状态枚举
 *
 * @author YangZhang
 * @createTime 2023/10/02/ 19:12
 */
@RequiredArgsConstructor
public enum IdempotentMQConsumeStatusEnum {

    /**
     * 消费中
     */
    CONSUMING("0"),

    /**
     * 已消费
     */
    CONSUMED("1");

    @Getter
    private final String code;

    /**
     * 如果消费状态等于消费中，返回失败
     *
     * @param consumeStatus 消费状态
     * @return 是否消费失败
     */
    public static boolean isError(String consumeStatus) {
        return Objects.equals(CONSUMING.code, consumeStatus);
    }
}
