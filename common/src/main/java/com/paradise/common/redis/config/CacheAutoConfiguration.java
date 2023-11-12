package com.paradise.common.redis.config;

import com.paradise.common.redis.RedisKeySerializer;
import com.paradise.common.redis.StringRedisTemplateProxy;
import lombok.AllArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jrf
 * @date 2023-3-29 16:36
 */
@AllArgsConstructor
@EnableConfigurationProperties({RedisDistributedProperties.class, BloomFilterPenetrateProperties.class})
public class CacheAutoConfiguration {

    private final RedisDistributedProperties redisDistributedProperties;

    /**
     * 创建 Redis Key 序列化器，可自定义 Key Prefix
     */
    @Bean
    public RedisKeySerializer redisKeySerializer() {
        String prefix = redisDistributedProperties.getPrefix();
        String prefixCharset = redisDistributedProperties.getPrefixCharset();
        return new RedisKeySerializer(prefix, prefixCharset);
    }

    /**
     * 防止缓存穿透的布隆过滤器
     */
    @Bean
    @ConditionalOnProperty(prefix = BloomFilterPenetrateProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public RBloomFilter<String> cachePenetrationBloomFilter(RedissonClient redissonClient, BloomFilterPenetrateProperties bloomFilterPenetrateProperties) {
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter(bloomFilterPenetrateProperties.getName());
        cachePenetrationBloomFilter.tryInit(bloomFilterPenetrateProperties.getExpectedInsertions(), bloomFilterPenetrateProperties.getFalseProbability());
        return cachePenetrationBloomFilter;
    }

    @Bean
    // 静态代理模式: Redis 客户端代理类增强
    public StringRedisTemplateProxy stringRedisTemplateProxy(RedisKeySerializer redisKeySerializer,
                                                             StringRedisTemplate stringRedisTemplate,
                                                             RedissonClient redissonClient,
                                                             RBloomFilter rBloomFilter) {
        stringRedisTemplate.setKeySerializer(redisKeySerializer);
        return new StringRedisTemplateProxy(stringRedisTemplate, redisDistributedProperties, redissonClient,rBloomFilter);
    }

    /**
     * 初始化RedissonClient客户端
     * 注意：
     * 此实例集群为3节点，各节点1主1从
     * 集群模式,集群节点的地址须使用“redis://”前缀，否则将会报错。
     *
     * @param redisProperties {@link RedisProperties}
     * @return {@link RedissonClient}
     */
    @Bean
    public RedissonClient getRedissonClient(RedisProperties redisProperties) {
        Config config = new Config();
        if (redisProperties.getCluster() != null) {
            //集群模式配置
            List<String> nodes = redisProperties.getCluster().getNodes();
            nodes = nodes.stream().map(node -> "redis://" + node).collect(Collectors.toList());
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            clusterServersConfig.addNodeAddress(nodes.toArray(new String[nodes.size()]));
            if (!StringUtils.isEmpty(redisProperties.getPassword())) {
                clusterServersConfig.setPassword(redisProperties.getPassword());
            }
        } else {
            //单节点配置
            String address = "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort();
            SingleServerConfig serverConfig = config.useSingleServer();
            serverConfig.setAddress(address);
            if (!StringUtils.isEmpty(redisProperties.getPassword())) {
                serverConfig.setPassword(redisProperties.getPassword());
            }
            serverConfig.setDatabase(redisProperties.getDatabase());
        }
        //看门狗的锁续期时间，默认30000ms，这里配置成15000ms
        config.setLockWatchdogTimeout(15000);
        return Redisson.create(config);
    }
}
