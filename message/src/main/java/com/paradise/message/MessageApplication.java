package com.paradise.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author jrf
 * @date 2023-3-29 13:26
 */
@SpringBootApplication(scanBasePackages = "com.paradise.message")
@EnableFeignClients("com.paradise.message.send")//开启扫描目录下的feign接口
@EnableAsync//开启异步
@EnableHystrix//开启服务降级
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class,args);
    }

}
