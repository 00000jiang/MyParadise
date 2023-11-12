package com.paradise.common.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author jrf
 * @date 2023-3-29 16:36
 */
@Data
@ConfigurationProperties(prefix = RedisDistributedProperties.PREFIX)
public class RedisDistributedProperties {

    public static final String PREFIX = "congomall.cache.redis";

    /**
     * Key 前缀
     */
    private String prefix = "";

    /**
     * Key 前缀字符集
     */
    private String prefixCharset = "UTF-8";

    /**
     * 默认超时时间
     */
    private Long valueTimeout = 30000L;

    /**
     * 时间单位
     */
    private TimeUnit valueTimeUnit = TimeUnit.MILLISECONDS;
}
