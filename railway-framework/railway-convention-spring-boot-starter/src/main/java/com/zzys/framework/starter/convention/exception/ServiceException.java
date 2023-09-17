package com.zzys.framework.starter.convention.exception;

import com.zzys.framework.starter.convention.errorcode.BaseErrorCode;
import com.zzys.framework.starter.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * 服务端异常
 *
 * @author YangZhang
 * @createTime 2023/09/17/ 20:51
 */
public class ServiceException extends AbstractException{
    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
