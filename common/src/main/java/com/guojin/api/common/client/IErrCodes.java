package com.guojin.api.common.client;

/**
 * @describe: 系统错误码.detailCode: 1000以内预留给系统公用, 自定义错误码从1000往后使用
 * @author: guojin
 * @date: 2018/8/2 10:24
 **/
public interface IErrCodes {
    /**
     * @describe: 错误类别
     * @date: 2018/8/2 10:25
     **/
    long getTypeCode();

    /**
     * @describe: 错误码
     * @date: 2018/8/2 10:25
     **/
    long getDetailCode();

    /**
     * @describe: 错误信息
     * @date: 2018/8/2 10:25
     **/
    String getErrMsgPattern();
}
