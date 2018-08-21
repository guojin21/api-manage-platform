package com.guojin.api.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.guojin.api.common.client.ResultCodeProvider;
import com.guojin.api.common.constant.CommonErrCodes;
import com.guojin.api.common.constant.ModuleCodeEnum;
import com.guojin.api.common.constant.SubModuleCode;
import com.guojin.api.gateway.limiter.LuaRateLimiter;
import com.guojin.api.gateway.limiter.PerUriRateLimitPolicy;
import com.guojin.api.gateway.limiter.RateLimitPolicy;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;


/**
 * @describe: 接口限速限流过滤器
 * @author: guojin
 * @date: 2018/8/21 16:23
 **/
public class RateLimiterBefore extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(RateLimiterBefore.class);

    private static ResultCodeProvider rCodeProvider = new ResultCodeProvider(ModuleCodeEnum.API_MANAGE_PLATFORM, SubModuleCode.GATEWAY);

    @Autowired
    private LuaRateLimiter luaRateLimiter;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("filter[%s] : %s request to %s", "RateLimiterBefore", request.getMethod(), request.getRequestURI()));
        String apiURI = request.getRequestURI();
        RateLimitPolicy rateLimitPolicy = new PerUriRateLimitPolicy(10, 20000, 10000);
        luaRateLimiter.setPolicy(rateLimitPolicy);

        //根据接口地址（不含参）进行限速
        if (luaRateLimiter.access(apiURI)) {
            logger.info("rate limiter is successful");
        } else {
            logger.info("rate limiter is failed");
            //过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
            //设置其返回的错误码
            ctx.setResponseStatusCode(401);
            //对返回body内容进行编辑
            ctx.setResponseBody(JSONObject.toJSONString(rCodeProvider.getRCode(CommonErrCodes.RATE_LIMITER_ERROR)));
        }
        return null;

    }
}
