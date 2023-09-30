package com.zzys.railway.framework.starter.cache.core.params;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.redisson.api.RBloomFilter;

import java.util.concurrent.TimeUnit;

/**
 * 获取分布式缓存参数
 *
 * @author YangZhang
 * @createTime 2023/09/30/ 23:50
 */
@Builder
@Getter
public class PutDistributedCacheParams {
    @NonNull
    private String key;
    @NonNull
    private Object value;
    private long timeout;
    private TimeUnit timeUnit;
    private RBloomFilter<String> bloomFilter;
}
