package com.guojin.api.common.exception;


import com.guojin.api.common.client.IErrCodes;
import com.guojin.api.common.constant.CommonErrCodes;

/**
 * @describe: 自定义运行时异常基类
 * @author: guojin
 * @date: 2018/8/2 10:38
 **/
public class RuntimeExceptionBase extends RuntimeException {
    private IErrCodes errCodes;
    private String[] errMsgPatternParams;

    public RuntimeExceptionBase(IErrCodes errCodes, String ... errMsgPatternParams) {
        super(errCodes.getDetailCode() + errCodes.getErrMsgPattern());
        this.errCodes = errCodes;
        this.errMsgPatternParams = errMsgPatternParams;
    }

    public RuntimeExceptionBase(Throwable e) {
        super(e);
        throwableTrans(e);
    }

    public RuntimeExceptionBase(String msg, Throwable e) {
        super(msg, e);
        throwableTrans(e);
    }

    public RuntimeExceptionBase(IErrCodes errCodes, Throwable e, String ... errMsgPatternParams) {
        super(errCodes.getDetailCode() + errCodes.getErrMsgPattern(), e);
        this.errCodes = errCodes;
        this.errMsgPatternParams = errMsgPatternParams;
    }

    public void throwableTrans(Throwable e) {
        if (e instanceof RuntimeExceptionBase) {
            RuntimeExceptionBase web = (RuntimeExceptionBase) e;
            this.errCodes = web.getErrCodes();
            this.errMsgPatternParams = web.getErrMsgPatternParams();
        } else if (e instanceof IllegalArgumentException) {
            IllegalArgumentException iae = (IllegalArgumentException) e;

            this.errCodes = CommonErrCodes.INVALID_PARAM_FOR;
            this.errMsgPatternParams = new String[]{e.getMessage()};
        } else {
            this.errCodes = CommonErrCodes.INTERNAL_ERROR;
        }
    }

    public IErrCodes getErrCodes() {
        return errCodes;
    }

    public void setErrCodes(IErrCodes errCodes) {
        this.errCodes = errCodes;
    }

    public String[] getErrMsgPatternParams() {
        return errMsgPatternParams;
    }

    public void setErrMsgPatternParams(String[] errMsgPatternParams) {
        this.errMsgPatternParams = errMsgPatternParams;
    }
}
