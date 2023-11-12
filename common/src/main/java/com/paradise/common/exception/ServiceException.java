package com.paradise.common.exception;

import com.paradise.common.errorcode.BaseErrorCode;
import com.paradise.common.errorcode.IErrorCode;

/**
 * 服务端异常
 * @author jrf
 * @date 2023-3-29 14:08
 */
public class ServiceException extends AbstractException{

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.B0001);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }

}
