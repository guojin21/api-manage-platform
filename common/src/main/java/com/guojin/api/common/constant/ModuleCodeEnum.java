package com.guojin.api.common.constant;


/**
 * @describe: 主模块编码
 * @author: guojin
 * @date: 2018/8/2 10:35
 **/
public enum ModuleCodeEnum {
    API_MANAGE_PLATFORM(1L),;

    private long moduleCode;

    ModuleCodeEnum(long moduleCode) {
        this.moduleCode = moduleCode;
    }

    public long getModuleCode() {
        return this.moduleCode;
    }
}
