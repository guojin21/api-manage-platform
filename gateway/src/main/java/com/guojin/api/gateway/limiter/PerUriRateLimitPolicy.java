package com.guojin.api.gateway.limiter;

/**
 * @describe: 实现按照URI进行限速的策略
 * @author: guojin
 * @date: 2018/8/21 16:43
 **/
public class PerUriRateLimitPolicy extends RateLimitPolicy {

    /**
     * @describe: 初始化
     * @date: 2018/8/21 16:44
     **/
    public PerUriRateLimitPolicy(long capacity, long intervalInMills, long maxBurstTime) {
        super(capacity, intervalInMills, maxBurstTime);
    }

    @Override
    public String genBucketKey(String identity) {
        return "rate:limiter:" + getIntervalInMills() + ":" + getCapacity() + ":" + identity;
    }
}
