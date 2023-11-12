package com.paradise.common.errorcode;

/**
 * @author jrf
 * @date 2023-3-29 14:02
 */
public enum BaseErrorCode implements IErrorCode{

    // ========== 一级宏观错误码 客户端错误 ==========
    A0001("A0001", "用户端错误"),

    // ========== 二级宏观错误码 用户注册错误 ==========
    A0002("A0002", "用户注册错误"),
    A0003("A0003", "用户名校验失败"),
    A0004("A0004", "用户名已存在"),
    A0005("A0005", "用户名包含敏感词"),
    A0006("A0006", "用户名包含特殊字符"),
    A0007("A0007", "密码校验失败"),
    A0008("A0008", "密码长度不够"),
    A0009("A0009", "手机格式校验失败"),

    // ========== 二级宏观错误码 系统请求缺少幂等Token ==========
    A0010("A0010", "Token为空"),
    A0011("A0011", "Token已被使用或失效"),
    A0012("A0012", "空指针异常"),

    // ========== 一级宏观错误码 系统执行出错 ==========
    B0001("B0001", "系统执行出错"),
    // ========== 二级宏观错误码 系统执行超时 ==========
    B0100("B0100", "系统执行超时"),

    // ========== 一级宏观错误码 调用第三方服务出错 ==========
    C0001("C0001", "调用第三方服务出错"),

    // ========== 一级宏观错误码 调用第三方服务出错 ==========
    T0000("T0000", "token验证失败"),
    T0001("T0001", "token已经过期"),
    T0003("T0002", "无效签名"),
    T0004("T0003", "算法异常");
    private final String code;

    private final String message;

    BaseErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
