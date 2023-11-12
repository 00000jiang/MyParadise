package com.paradise.common.uuid;

/**
 * @author jrf
 * @date 2023-3-30 18:44
 */
public interface IdGenerator {

    /**
     * 下一个 ID
     */
    default long nextId() {
        return 0L;
    }

    /**
     * 下一个 ID 字符串
     */
    default String nextIdStr() {
        return "";
    }

}
