package com.zzys.framework.starter.convention.exception;

import com.zzys.framework.starter.convention.errorcode.BaseErrorCode;
import com.zzys.framework.starter.convention.errorcode.IErrorCode;

/**
 * RPC异常
 *
 * @author YangZhang
 * @createTime 2023/09/17/ 20:50
 */
public class RemoteException extends AbstractException{
    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
