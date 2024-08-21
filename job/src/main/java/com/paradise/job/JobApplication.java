package com.paradise.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author: Cookies
 * @date: ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}
 **/
@SpringBootApplication(scanBasePackages = "com.paradise.job")
@EnableFeignClients("com.paradise.job.remote")
@EnableAsync
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }

}
