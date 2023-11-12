package com.paradise.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.paradise.common.result.Result;
import com.paradise.common.result.Results;
import com.paradise.common.utils.JWTUitls;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息 前端控制器
 * @author jiangrenfeng
 * @since 2023-03-30
 */
@Api(tags = "用户接口")
@RestController
public class UserController {

    @GetMapping("/login")
    public Result<String> login(@RequestParam("id") String id) throws Exception {
        String sign = JWTUitls.sign("123456789");
        return Results.success(sign);
    }

    @GetMapping("/getMyTest/{value}")
    public Result<String> getMyTest(@PathVariable("value") String value){
        return Results.success(value);
    }


    @GetMapping("/getUserInfo")
    public Result<String> getUserInfo(@RequestParam("id") String id){
        return Results.success(id);
    }

    @GetMapping("/getJson")
    public Result<JSONObject> getMygetJsonTest(){
        JSONObject object = new JSONObject();
        object.put("id",1);
        object.put("name","张三");
        return Results.success(object);
    }

}
