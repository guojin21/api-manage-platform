package com.guojin.api.common.constant;


import com.guojin.api.common.client.IErrCodes;

/**
 * @describe: 通用系统错误码
 * @author: guojin
 * @date: 2018/8/2 10:50
 **/
public enum CommonErrCodes implements IErrCodes {
    /**客户端错误*/
    BAD_REQUEST(4L, 0L, "Bad Request"),
    BAD_REQUEST_FOR(4L, 1L, "Bad Request:%s"),
    NOT_FOUND(4L, 4L, "Not Found"),
    INVALID_PARAM(4L, 100L, "Invalid Param"),
    INVALID_PARAM_FOR(4L, 101L, "Invalid Param [%s]"),
    PARAM_ERROR(4L, 102L, "%s"),
    MISSING_PARAM(4L, 103L, "Missing Param [%s]"),
    USER_NOT_EXISTS(4L, 104L, "用户不存在"),
    RECORD_NOT_EXISTS(4L, 105L, "记录不存在"),
    RATE_LIMITER_ERROR(4L, 106L, "The call rate is too fast"),
    /**系统错误 */
    INTERNAL_ERROR(5L, 0L, "Internal server error"),
    INTERNAL_ERROR_FOR(5L, 1L, "Internal server error for [%s]"),
    /**权限错误*/
    FORBIDDEN(7L, 3L, "Forbidden"),
    ;

    private long typeCode;
    private long detailCode;
    private String errMsgPattern;

    CommonErrCodes(long typeCode, long detailCode, String errMsgPattern) {
        this.typeCode = typeCode;
        this.detailCode = detailCode;
        this.errMsgPattern = errMsgPattern;
    }

    @Override
    public long getTypeCode() {
        return this.typeCode;
    }

    @Override
    public long getDetailCode() {
        return this.detailCode;
    }

    @Override
    public String getErrMsgPattern() {
        return this.errMsgPattern;
    }
}
