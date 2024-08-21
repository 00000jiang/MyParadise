package com.paradise.message.controller;

import com.paradise.common.result.Result;
import com.paradise.common.result.Results;
import com.paradise.message.send.UserSendMessageService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jrf
 * @date 2023-3-29 18:37
 */
@Api(tags = "消息控制层")
@RestController
public class MessageController {

    @Resource
    UserSendMessageService userSendMessageService;

    @GetMapping("/myMessage")
    public Result<String> myMessage(@RequestParam("value") String value) throws Exception {
        //throw new Exception("error-myMessage");
        return Results.success(value);
    }

    @GetMapping("/updateUser/{id}")
    public Result<String> updateUser(@PathVariable("id") String id) throws Exception {
        Result result = userSendMessageService.updateUserInfo(id);
        //throw new ClientException("user报错", BaseErrorCode.B0001);
        return result;
    }


}
