package com.guojin.api.gateway.limiter;


import com.google.common.base.Preconditions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;

public class LuaRateLimiter {

    RedisScript<Long> script;

    private RateLimitPolicy policy;

    private StringRedisTemplate stringRedisTemplate;

    public LuaRateLimiter(RedisScript<Long> script, StringRedisTemplate stringRedisTemplate) {
        this.script =script;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public final boolean access(final String identity) {
        Preconditions.checkNotNull(policy, "Policy not initialized");

        String key = policy.genBucketKey(identity);
        long result = stringRedisTemplate.execute(
                script,
                Collections.singletonList(key),
                String.valueOf(policy.getIntervalPerPermit()),
                String.valueOf(System.currentTimeMillis()),
                String.valueOf(policy.getMaxBurstTokens()),
                String.valueOf(policy.getCapacity()),
                String.valueOf(policy.getIntervalInMills()));
        return result == 1L;
    }

    public void setPolicy(RateLimitPolicy policy) {
        this.policy = policy;
    }
}
