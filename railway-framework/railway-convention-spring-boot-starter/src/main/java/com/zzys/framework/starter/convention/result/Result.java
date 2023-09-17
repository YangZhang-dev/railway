package com.zzys.framework.starter.convention.result;

import java.io.Serial;
import java.io.Serializable;

/**
 * 响应类
 *
 * @author YangZhang
 * @createTime 2023/09/17/ 21:08
 */
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 5679018624309023727L;

    /**
     * 正确返回码
     */
    public static final String SUCCESS_CODE = "0";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 请求ID
     */
    private String requestId;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
