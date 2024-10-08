package com.paradise.common.result.page;

import lombok.Data;

/**
 * 分页请求对象
 */
@Data
public class PageRequest {

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页显示条数
     */
    private Long size;
}
