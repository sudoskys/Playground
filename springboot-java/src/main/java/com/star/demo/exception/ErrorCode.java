package com.star.demo.exception;

public enum ErrorCode {
    EMAIL_ALREADY_EXISTS("AUTH001", "邮箱已被注册"),
    INVALID_CREDENTIALS("AUTH002", "无效的凭证"),
    VALIDATION_ERROR("VAL001", "输入验证失败"),
    INTERNAL_SERVER_ERROR("SYS001", "系统内部错误"),
    DATABASE_ERROR("DB001", "数据库操作错误");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
