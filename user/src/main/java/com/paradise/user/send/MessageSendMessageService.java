package com.paradise.user.send;

import com.paradise.common.result.Result;
import com.paradise.user.fallback.MessageFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jrf
 * @date 2023-3-29 18:34
 */
@Component
@FeignClient(name = "message",fallbackFactory = MessageFallback.class)
public interface MessageSendMessageService {

    @GetMapping("/myMessage")
    Result myMessage(@RequestParam("value") String value);

}
