package com.paradise.common.exception;

import com.paradise.common.errorcode.IErrorCode;

/**
 * 远程服务调用异常
 * @author jrf
 * @date 2023-3-29 14:07
 */
public class RemoteException extends AbstractException{

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
