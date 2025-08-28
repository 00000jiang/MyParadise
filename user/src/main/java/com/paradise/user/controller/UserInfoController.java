package com.paradise.user.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.paradise.common.redis.CustomizeRedisTemplate;
import com.paradise.common.result.Result;
import com.paradise.common.result.Results;
import com.paradise.common.utils.JWTUitls;
import com.paradise.user.async.UserAsyncService;
import com.paradise.user.entity.po.UserInfo;
import com.paradise.user.send.MessageSendMessageService;
import com.paradise.user.service.impl.UserInfoServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户信息 前端控制器
 * @author jiangrenfeng
 * @since 2023-03-30
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    CustomizeRedisTemplate customizeRedisTemplate;

    @Resource
    MessageSendMessageService messageSendMessageService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserInfoServiceImpl userInfoService;

    @Autowired
    UserAsyncService userAsyncService;

    @GetMapping("/getMyTest/{value}")
    public Result<String> getMyTest(@PathVariable("value") String value){
        //使用redis缓存
        customizeRedisTemplate.put("jiangrenfeng1234","11122");
        //利用本地缓存
        //customizeRedisTemplate.safeGet("jiangrenfeng1234", String.class, () -> "test_value", 5000L);
        String actual = customizeRedisTemplate.get("jiangrenfeng1234", String.class);
        System.out.println(actual);
        Result data = messageSendMessageService.myMessage(value);
        return data;
    }

    @GetMapping("/testMq")
    public Result<String> testMq(){
        rabbitTemplate.convertAndSend("TestDirect1","TestBinding1","testMq");
        return Results.success("测试");
    }


    @GetMapping("/getUserInfo")
    public Result<UserInfo> getUserInfo(@RequestParam("id") String id){
        UserInfo byUser = userInfoService.getByUser(id);
        return Results.success(byUser);
    }

    @GetMapping("/updateUserInfo")
    @Transactional(rollbackFor = Exception.class)
    public Result updateUserInfo(@RequestParam("id") String id) throws Exception {
        //userInfoService.updateUser(id);
        throw new Exception("报错");
        //return Results.success();
    }

    @GetMapping("/login")
    public Result<String> login(@RequestParam("id") String id){
        UserInfo byUser = userInfoService.getByUser(id);
        String data = JSONObject.toJSONString(byUser);
        String sign = JWTUitls.sign(data);
        /*EagerThreadPoolExecutor threadPoolExecutor = new EagerThreadPoolExecutor(50,1000,5000L, TimeUnit.MILLISECONDS,new TaskQueue(50));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UserInfo byUser = userInfoService.getByUser(id);
                String data = JSONObject.toJSONString(byUser);
                JWTUitls.sign(data);
            }
        });*/
         /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50,1000,5000L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(50));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UserInfo byUser = userInfoService.getByUser(id);
                String data = JSONObject.toJSONString(byUser);
                JWTUitls.sign(data);
            }
        });*/
        /*UserInfo byUser = userInfoService.getByUser(id);
        String data = JSONObject.toJSONString(byUser);
        String sign = JWTUitls.sign(data);*/
        return Results.success(sign);
    }

    int i = 0;
    int j = 0;

    @GetMapping("/test")
    @SentinelResource(value = "test", blockHandler = "handleBlock")
    public Object test(){
        return "成功";
    }
    @GetMapping("/test2")
    @SentinelResource(value = "test2", blockHandler = "handleBlock2")
    public Object test2() throws InterruptedException {
        if (i%2==0) {
            Thread.sleep(300);
            j++;
        }
        i++;
        return "成功";
    }

    // 限流处理函数
    public String handleBlock(BlockException ex) {
        System.out.println("--------限流处理函数");
        return "限流请求过于频繁，请稍后再试";
    }

    // 限流处理函数
    public String handleBlock2(BlockException ex) {
        System.out.println("--------熔断处理函数"+j);
        return "熔断请求过于频繁，请稍后再试";
    }
}
