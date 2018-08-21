package com.guojin.api.common.client;


import com.guojin.api.common.constant.ModuleCodeEnum;
import com.guojin.api.common.constant.SubModuleCode;

/**
 * @author: guojin
 * @date: 2018/8/2 10:33
 **/
public class ResultCodeProvider {

    private ModuleCodeEnum moduleCodeEnum;

    private ISubModuleCode subModuleCode;

    public ResultCodeProvider (ModuleCodeEnum moduleCodeEnum, ISubModuleCode subModuleCode) {
        this.moduleCodeEnum = moduleCodeEnum;
        this.subModuleCode = subModuleCode;
    }

    public ResultCodeProvider (ModuleCodeEnum moduleCodeEnum) {
        this.moduleCodeEnum = moduleCodeEnum;
        this.subModuleCode = SubModuleCode.NO_SUBMODULE;
    }

    public <T> ResultCode<T> getRCode() {
        return new ResultCode();
    }

    public <T> ResultCode<T> getRCode(T data) {
        return new ResultCode(data);
    }

    public <T> ResultCode<T> getRCode(IErrCodes errCodes) {
        return new ResultCode(this.moduleCodeEnum, this.subModuleCode, errCodes, null);
    }

    public <T> ResultCode<T> getRCode(IErrCodes errCodes, T data) {
        return new ResultCode(this.moduleCodeEnum, this.subModuleCode, errCodes, data);
    }

    public <T> ResultCode<T> getRCode(IErrCodes errCodes, T data, Object... params) {
        return new ResultCode(this.moduleCodeEnum, this.subModuleCode, errCodes, data, params);
    }
}
