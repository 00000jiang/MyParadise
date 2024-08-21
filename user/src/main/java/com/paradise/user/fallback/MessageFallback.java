package com.paradise.user.fallback;

import com.paradise.common.result.Result;
import com.paradise.common.result.Results;
import com.paradise.user.send.MessageSendMessageService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author jrf
 * @date 2023-4-3 18:49
 */
@Component
public class MessageFallback implements FallbackFactory<MessageSendMessageService> {

    @Override
    public MessageSendMessageService create(Throwable throwable) {
        return new MessageSendMessageService() {
            @Override
            public Result myMessage(String value) {
                return Results.success("报错-error");
            }
        };
    }
}
