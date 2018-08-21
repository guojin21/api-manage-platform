package com.guojin.api.common.constant;


import com.guojin.api.common.client.ISubModuleCode;

/**
 * @describe: 子模块编码
 * @author: guojin
 * @date: 2018/8/2 10:36
 **/
public enum  SubModuleCode implements ISubModuleCode {
    NO_SUBMODULE(0L),
    COMMON(1L),
    OAUTH_SERVER(2L),
    API_CLIENT(3L),
    RESOURCE_SERVER(4L),
    GATEWAY(5L),
    ;

    private long subModuleCode;

    SubModuleCode(long subModuleCode) {
        this.subModuleCode = subModuleCode;
    }

    @Override
    public long getSubModuleCode() {
        return subModuleCode;
    }
}
