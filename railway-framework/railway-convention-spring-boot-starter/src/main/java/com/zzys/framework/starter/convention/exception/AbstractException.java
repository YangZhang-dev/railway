package com.zzys.framework.starter.convention.exception;

import com.zzys.framework.starter.convention.errorcode.IErrorCode;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 抽象异常体系
 *
 * @author YangZhang
 * @createTime 2023/09/17/ 20:47
 */
public class AbstractException extends RuntimeException{
    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null).orElse(errorCode.message());
    }
}
