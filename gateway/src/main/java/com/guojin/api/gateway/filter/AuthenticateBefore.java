package com.guojin.api.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.guojin.api.common.client.ResultCodeProvider;
import com.guojin.api.common.constant.CommonErrCodes;
import com.guojin.api.common.constant.ModuleCodeEnum;
import com.guojin.api.common.constant.SubModuleCode;
import com.guojin.api.gateway.service.IAuthenticateProxyService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;


/**
 * @describe: oAuth鉴权过滤器
 * @author: guojin
 * @date: 2018/8/21 16:50
 **/
public class AuthenticateBefore extends ZuulFilter {

    @Autowired
    private IAuthenticateProxyService authenticateProxyService;

    private static Logger logger = LoggerFactory.getLogger(RateLimiterBefore.class);

    private static ResultCodeProvider rCodeProvider = new ResultCodeProvider(ModuleCodeEnum.API_MANAGE_PLATFORM, SubModuleCode.GATEWAY);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("filter[%s] : %s request to %s", "AuthenticateBefore", request.getMethod(), request.getRequestURI()));
        if (authenticateProxyService.doAutenticate(request)) {
            //鉴权成功
            logger.info("authentication is successful");
        } else {
            //鉴权失败
            logger.warn("authentication is failed");
            //过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
            //设置其返回的错误码
            ctx.setResponseStatusCode(401);
            //对返回body内容进行编辑
            ctx.setResponseBody(JSONObject.toJSONString(rCodeProvider.getRCode(CommonErrCodes.FORBIDDEN)));
        }
        return null;
    }
}
