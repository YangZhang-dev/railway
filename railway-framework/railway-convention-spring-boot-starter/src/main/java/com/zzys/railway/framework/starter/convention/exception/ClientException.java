package com.zzys.railway.framework.starter.convention.exception;

import com.zzys.railway.framework.starter.convention.errorcode.BaseErrorCode;
import com.zzys.railway.framework.starter.convention.errorcode.IErrorCode;
import lombok.ToString;

/**
 * 客户端异常
 *
 * @author YangZhang
 * @createTime 2023/09/17/ 20:48
 */
@ToString
public class ClientException extends AbstractException{
    public ClientException(IErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }

    public ClientException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
