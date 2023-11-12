package com.paradise.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtils {
    /**
     * 获取请求头信息
     *
     * @return
     */
    public static HttpServletRequest getHeader() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }


    /**
     * 获取请求头信息
     *
     * @return
     */
    public static String getToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest().getHeader("lanlinker-partner");
    }
}
