package com.guojin.api.common.constant;

/**
 * @describe: 错误码类型枚举
 * @author: guojin
 * @date: 2018/8/2 10:50
 **/
public enum ErrTypeEnum {
    NOT_DEFINE(-1L),
    SUCCESS(2L),
    CLIENT_ERROR(4L),
    SERVER_ERROR(5L),
    SECURITY_ERROR(8L),
    ;

    private long typeCode;

    ErrTypeEnum(long typeCode) {
        this.typeCode = typeCode;
    }

    public long getTypeCode(){
        return this.typeCode;
    }

    public static ErrTypeEnum valueOf (long typeCode) {
        for (ErrTypeEnum e : ErrTypeEnum.values()) {
            if (e.getTypeCode() == typeCode) {
                return e;
            }
        }

        return NOT_DEFINE;
    }

}
