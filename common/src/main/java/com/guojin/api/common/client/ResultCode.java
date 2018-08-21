package com.guojin.api.common.client;


import com.google.gson.Gson;
import com.guojin.api.common.constant.CommonErrCodes;
import com.guojin.api.common.constant.ErrTypeEnum;
import com.guojin.api.common.constant.ModuleCodeEnum;

import java.io.Serializable;

/**
 * @describe: 返回给前端的通用结构
 * @author: guojin
 * @date: 2018/8/2 10:26
 **/
public class ResultCode<T> implements Serializable {
    public static final Long SUCCESS_CODE = 0L;
    /**
     * 错误码
     */
    Long errCode;
    /**
     * 错误信息
     */
    String errMsg;

    String errMagPattern;
    /**
     * 数据体
     */
    T data;

    public ResultCode() {
        this.errCode = SUCCESS_CODE;
        this.errMsg = "success";
        this.errMagPattern = "success";
    }

    public ResultCode(T data) {
        this.errCode = SUCCESS_CODE;
        this.data = data;
        this.errMsg = "success";
        this.errMagPattern = "success";
    }

    public ResultCode(Long errCode, T data, String errMsg) {
        this.errCode = errCode;
        this.data = data;
        this.errMsg = errMsg;
    }

    public ResultCode(ModuleCodeEnum mcu, ISubModuleCode smc, IErrCodes errCodes, T data, Object... params) {
        if (errCodes.getDetailCode() < 1000L && errCodes.getClass() != CommonErrCodes.class) {
            throw new RuntimeException("0-1000 reserve for CommonErrCodes");
        } else {
            long typeCode = errCodes.getTypeCode() % 10L;
            long moduleCode = mcu.getModuleCode() % 100L;
            long subModuleCode = smc.getSubModuleCode() % 100L;
            long detailErrCode = errCodes.getDetailCode() % 10000L;

            this.errCode = typeCode * 100L * 100L * 100L * 10000L +
                                       77L * 100L * 100L * 10000L +
                                       moduleCode * 100L * 10000L +
                                           subModuleCode * 10000L +
                                                        detailErrCode;

            this.errMsg = String.format(errCodes.getErrMsgPattern(), params);
            this.data = data;
        }
    }

    public static ErrTypeEnum getErrType(Long errCode) {
        if (SUCCESS_CODE.equals(errCode)) {
            return ErrTypeEnum.SUCCESS;
        } else {
            return ErrTypeEnum.valueOf(errCode % 10L);
        }
    }

    public Long getErrCode() {
        return errCode;
    }

    public void setErrCode(Long errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }
}
