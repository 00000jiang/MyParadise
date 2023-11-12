package com.paradise.common.result;

import com.paradise.common.errorcode.BaseErrorCode;
import com.paradise.common.exception.AbstractException;
import com.paradise.common.uuid.SnowflakeIdUtil;

import java.util.Optional;

/**
 * @author jrf
 * @date 2023-3-29 14:00
 */
public class Results {

    /**
     * 构造成功响应
     *
     * @return
     */
    public static Result<Void> success() {
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE)
                .setRequestId(SnowflakeIdUtil.nextIdStr());
    }

    /**
     * 构造带返回数据的成功响应
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setCode(Result.SUCCESS_CODE)
                .setRequestId(SnowflakeIdUtil.nextIdStr())
                .setData(data);
    }

    /**
     * 构建服务端失败响应
     *
     * @return
     */
    public static Result<Void> failure() {
        return new Result<Void>()
                .setCode(BaseErrorCode.B0001.code())
                .setRequestId(SnowflakeIdUtil.nextIdStr())
                .setMessage(BaseErrorCode.B0001.message());
    }

    /**
     * 通过 {@link AbstractException} 构建失败响应
     *
     * @param abstractException
     * @return
     */
    public static Result<Void> failure(AbstractException abstractException) {
        String errorCode = Optional.ofNullable(abstractException.getErrorCode())
                .orElse(BaseErrorCode.B0001.code());
        String errorMessage = Optional.ofNullable(abstractException.getErrorMessage())
                .orElse(BaseErrorCode.B0001.message());
        return new Result<Void>()
                .setCode(errorCode)
                .setRequestId(SnowflakeIdUtil.nextIdStr())
                .setMessage(errorMessage);
    }

    /**
     * 通过 errorCode、errorMessage 构建失败响应
     *
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static Result<Void> failure(String errorCode, String errorMessage) {
        return new Result<Void>()
                .setCode(errorCode)
                .setRequestId(SnowflakeIdUtil.nextIdStr())
                .setMessage(errorMessage);
    }

}
