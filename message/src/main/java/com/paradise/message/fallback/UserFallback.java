package com.paradise.message.fallback;

import com.paradise.common.errorcode.BaseErrorCode;
import com.paradise.common.exception.ClientException;
import com.paradise.common.result.Result;
import com.paradise.message.send.UserSendMessageService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author jrf
 * @date 2023-4-3 18:49
 */
@Component
public class UserFallback implements FallbackFactory<UserSendMessageService> {

    @Override
    public UserSendMessageService create(Throwable throwable) {
        return new UserSendMessageService() {
            @Override
            public Result updateUserInfo(String value) {
                throw new ClientException("user报错",BaseErrorCode.B0001);
                //return Results.failure(new RemoteException("user报错", throwable, BaseErrorCode.B0001));
            }
        };
    }
}
