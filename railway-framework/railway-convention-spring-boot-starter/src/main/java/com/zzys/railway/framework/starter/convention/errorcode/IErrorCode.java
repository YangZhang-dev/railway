package com.zzys.railway.framework.starter.convention.errorcode;

/**
 * 平台错误码
 *
 * @author YangZhang
 * @createTime 2023/09/17/ 20:44
 */
public interface IErrorCode {
    /**
     * 错误码
     */
    String code();
    /**
     * 错误码描述
     */
    String message();
}
