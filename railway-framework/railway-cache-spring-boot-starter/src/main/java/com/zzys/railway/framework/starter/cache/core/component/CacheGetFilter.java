package com.zzys.railway.framework.starter.cache.core.component;

/**
 * 缓存过滤
 *
 * @author YangZhang
 * @createTime 2023/09/30/ 23:28
 */
@FunctionalInterface
public interface CacheGetFilter<T> {

    /**
     * 缓存过滤
     *
     * @param param 输出参数
     * @return {@code true} 如果输入参数匹配，否则 {@link Boolean#TRUE}
     */
    boolean filter(T param);
}
