package com.bluesky.admin.api.common.exception;

/**
 * @author Lijinchun
 * @Package com.wywk.member.expiredclean.common.exception
 * @date 2019/7/24 13:59
 */
public class ServiceException extends RuntimeException {
    private final String code;
    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String message) {
        super(message);
        this.code = null;
        this.msg = message;
    }
}
