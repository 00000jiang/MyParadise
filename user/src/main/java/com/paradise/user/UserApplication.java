package com.paradise.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author jrf
 * @date 2023-3-29 13:43
 */
@SpringBootApplication(scanBasePackages = "com.paradise.user")
@MapperScan("com.paradise.user.mapper")//扫码数据库mapper文件
//@EnableFeignClients("com.paradise.user.send")//开启扫描目录下的feign接口
@EnableAsync//开启异步
//@EnableHystrix//开启服务降级
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
