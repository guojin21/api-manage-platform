package com.guojin.api.gateway.limiter;

/**
 * @describe: 限速策略基类
 * @author: guojin
 * @date: 2018/8/21 16:32
 **/
public abstract class RateLimitPolicy {
    /**
     * 间隔时间
     */
    private final long intervalInMills;
    /**
     * 总容量大小
     */
    private final long capacity;

    /**
     * 如果用户长时间不使用桶等于intervalPerPermit * maxBurstTime，则保存最大令牌
     */
    private final long maxBurstTokens;

    /**
     * 每个许可证耗时
     * 因为我们将使用intervalPerPermit通过mod计算时间左移填充，如果intervalPerPermit是浮点数，
     * 则计算结果将是浮点数。所以我在这里使用整数，它会失去一些精度，但我认为它有点可以忽略不计
     */
    private final long intervalPerPermit;

    public long getIntervalInMills() {
        return intervalInMills;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getMaxBurstTokens() {
        return maxBurstTokens;
    }

    public long getIntervalPerPermit() {
        return intervalPerPermit;
    }


    /**
     * @describe: 限速策略的初始化[capacity:总容量大小, intervalInMills：间隔时间, maxBurstTime：溢出总耗时]
     * @date: 2018/8/21 16:31
     **/
    public RateLimitPolicy(long capacity, long intervalInMills, long maxBurstTime) {
        this.capacity = capacity;
        this.intervalInMills = intervalInMills;
        //每个许可证耗时=20/10=2秒
        intervalPerPermit = intervalInMills / capacity;
        //溢出总耗时/每个许可证耗时=N个许可证  与  现有容量做对比，取最小值
        maxBurstTokens = Math.min(maxBurstTime/intervalPerPermit, capacity);
    }


    /**
     * @describe: 构建redis令牌的key
     * @date: 2018/8/21 16:43
     **/
    public abstract String genBucketKey(String identity);

}
