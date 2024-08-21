package com.paradise.job.user;

import com.alibaba.fastjson.JSONObject;
import com.paradise.common.result.Result;
import com.paradise.job.remote.SendMessageService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jrf
 * @date 2023-4-3 17:28
 */
@Component
@Slf4j
public class UserJob {

    @Resource
    SendMessageService sendMessageService;

    /**
     * 批量发送短信
     * @throws Exception
     */
    @XxlJob(value = "batchSendSms")
    public void batchSendSms(){
        Result aaasss = sendMessageService.myMessage("AAASSS");
        System.out.println(JSONObject.toJSONString(aaasss));
        System.out.println("batchSendSms");
    }

}
