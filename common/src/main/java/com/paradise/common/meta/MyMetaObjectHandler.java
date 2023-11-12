package com.paradise.common.meta;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.paradise.common.uuid.SnowflakeIdUtil;
import com.paradise.common.uuid.SnowflakeIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 * @author daisx
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时的填充策略
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        fillValue(metaObject, "uniqueNo", () -> SnowflakeIdUtil.nextIdStr());
        fillValue(metaObject, "createTime", () -> LocalDateTime.now());
        fillValue(metaObject, "delFlag", () -> "0");
    }

    /**
     * 更新时的填充策略
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        fillValue(metaObject, "updateTime", () -> LocalDateTime.now());
    }

    /**
     * 填充值
     * @param metaObject
     * @param fieldName
     * @param valueSupplier
     */
    private void fillValue(MetaObject metaObject, String fieldName, Supplier<Object> valueSupplier) {
        if (!metaObject.hasGetter(fieldName)) {
            return;
        }
        Object sidObj = metaObject.getValue(fieldName);
        if (sidObj == null && metaObject.hasSetter(fieldName) && valueSupplier != null) {
            setFieldValByName(fieldName, valueSupplier.get(), metaObject);
        }
    }

}
