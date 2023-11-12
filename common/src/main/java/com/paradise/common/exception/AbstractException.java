package com.paradise.common.exception;

import com.google.common.base.Strings;
import com.paradise.common.errorcode.IErrorCode;
import lombok.Getter;

import java.util.Optional;

/**
 * 抽象项目中三类异常体系，客户端异常、服务端异常以及远程服务调用异常
 * @author jrf
 * @date 2023-3-29 14:05
 */
@Getter
public class AbstractException extends RuntimeException{

    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(Strings.emptyToNull(message)).orElse(errorCode.message());
    }

}
