package com.paradise.message.send;

import com.paradise.common.result.Result;
import com.paradise.message.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jrf
 * @date 2023-3-29 18:34
 */
@Component
@FeignClient(name = "user",fallbackFactory = UserFallback.class)
public interface UserSendMessageService {

    @GetMapping("/updateUserInfo")
    Result updateUserInfo(@RequestParam("id") String id);

}
