package com.paradise.user.controller;


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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户信息 前端控制器
 * @author jiangrenfeng
 * @since 2023-03-30
 */
@Api(tags = "用户接口")
@RestController
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

}
