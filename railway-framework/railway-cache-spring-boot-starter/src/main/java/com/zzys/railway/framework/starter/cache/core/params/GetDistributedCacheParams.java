package com.zzys.railway.framework.starter.cache.core.params;

import com.zzys.railway.framework.starter.cache.core.component.CacheGetFilter;
import com.zzys.railway.framework.starter.cache.core.component.CacheGetIfAbsent;
import com.zzys.railway.framework.starter.cache.core.component.CacheLoader;
import lombok.Builder;
import lombok.NonNull;
import lombok.Getter;
import org.redisson.api.RBloomFilter;
import org.redisson.api.StreamInfo;

import java.util.concurrent.TimeUnit;

/**
 * 获取缓存参数
 *
 * @author YangZhang
 * @createTime 2023/09/30/ 23:21
 */
@Builder
@Getter
public class GetDistributedCacheParams<T> {
    @NonNull
    private String key;
    private Class<T> clazz;
    private CacheLoader<T> cacheLoader;
    private CacheGetIfAbsent<String> cacheGetIfAbsent;
    private CacheGetFilter<T> cacheGetFilter;
    private long timeout;
    private TimeUnit timeUnit;
    private RBloomFilter<String> bloomFilter;
}
