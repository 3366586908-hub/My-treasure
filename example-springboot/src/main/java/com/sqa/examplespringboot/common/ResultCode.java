package com.sqa.examplespringboot.common;

/**
 * 响应状态码
 * enum是java当中的枚举类
 */
public enum ResultCode {
    /**
     *
     * SUCCESS就是枚举类成员的名称
     * （一定要大写，单词与单词间用下划线连接）
     * 200就是code码，message就是想让前端显示给用户的信息
     * 调用方式举例：
     * 取code就是ResultCode.SUCCESS.getCode()
     * 取message就是ResultCode.SUCCESS.getMessage()
     * 如果这样写ResultCode.SUCCESS表示一个ResultCode类型
     * 如果我们想给用户显示的信息在这个枚举类中找不到，
     * 请自行添加给用户返回的信息，自定义！！！
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    USERNAME_OR_PASSWORD_ERROR(405, "用户名或密码错误"),
    USER_EXIST(406, "用户已存在"),
    DB_ERROR(500, "数据库操作失败"),
    
    // 参数错误 1000-1999
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    
    // 用户错误 2000-2999
    USER_NOT_LOGGED_IN(2001, "用户未登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
    USER_NOT_EXIST(2004, "用户不存在"),
    USER_HAS_EXISTED(2005, "用户已存在"),
    USER_PASSWORD_ERROR(2006, "密码错误"),
    
    // 业务错误 3000-3999
    BUSINESS_ERROR(3001, "业务逻辑异常"),
    DATA_NOT_FOUND(3002, "数据不存在"),
    DATA_IS_WRONG(3003, "数据有误"),
    DATA_ALREADY_EXISTED(3004, "数据已存在"),
    
    // 系统错误 4000-4999
    SYSTEM_INNER_ERROR(4001, "系统内部错误"),
    SYSTEM_BUSY(4002, "系统繁忙，请稍后重试"),
    
    // 接口错误 5000-5999
    INTERFACE_INNER_INVOKE_ERROR(5001, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(5002, "外部系统接口调用异常"),
    INTERFACE_FORBIDDEN(5003, "接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(5004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(5005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(5006, "接口负载过高");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
} 