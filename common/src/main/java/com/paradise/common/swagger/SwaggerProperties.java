package com.paradise.common.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chengzx
 * date 2021/8/31
 * description：
 */
@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * 子系统
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 版本号
     */
    private String version;

    /**
     * api包路径
     */
    private String basePackage;


}
