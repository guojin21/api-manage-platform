package com.guojin.api.gateway.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @describe: oauth鉴权代理
 * @author: guojin
 * @date: 2018/8/21 10:26
 **/
public interface IAuthenticateProxyService {

    /**
     * @describe: 执行鉴权
     * @date: 2018/8/21 10:27
     **/
    boolean doAutenticate(HttpServletRequest request);
}
