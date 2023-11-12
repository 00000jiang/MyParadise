package com.paradise.common.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jrf
 * @date 2023-3-29 16:31
 */
@Data
@ConfigurationProperties(prefix = BloomFilterPenetrateProperties.PREFIX)
public class BloomFilterPenetrateProperties {

    public static final String PREFIX = "congomall.cache.redis.bloom-filter.default";

    /**
     * 布隆过滤器默认实例名称
     */
    private String name = "cache_penetration_bloom_filter";

    /**
     * 每个元素的预期插入量
     */
    private Long expectedInsertions = 64000L;

    /**
     * 预期错误概率
     */
    private Double falseProbability = 0.03D;
}
