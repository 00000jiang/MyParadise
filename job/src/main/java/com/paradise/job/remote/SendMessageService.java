package com.paradise.job.remote;

import com.paradise.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jrf
 * @date 2023-4-3 17:33
 */
@Component
@FeignClient(name = "message")
public interface SendMessageService {

    @GetMapping("/myMessage/{value}")
    Result myMessage(@PathVariable("value") String value);

}
