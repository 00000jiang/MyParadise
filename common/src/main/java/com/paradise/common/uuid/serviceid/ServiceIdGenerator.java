package com.paradise.common.uuid.serviceid;

import com.paradise.common.uuid.IdGenerator;
import com.paradise.common.uuid.snowflake.SnowflakeIdInfo;

/**
 * @author jrf
 * @date 2023-3-30 18:41
 */
public interface ServiceIdGenerator extends IdGenerator {

    /**
     * 根据 {@param serviceId} 生成雪花算法 ID
     */
    long nextId(long serviceId);

    /**
     * 根据 {@param serviceId} 生成字符串类型雪花算法 ID
     */
    String nextIdStr(long serviceId);

    /**
     * 解析雪花算法
     */
    SnowflakeIdInfo parseSnowflakeId(long snowflakeId);

}
