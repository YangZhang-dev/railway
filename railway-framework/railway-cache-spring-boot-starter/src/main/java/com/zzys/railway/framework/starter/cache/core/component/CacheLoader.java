package com.zzys.railway.framework.starter.cache.core.component;

/**
 * 加载数据
 *
 * @author YangZhang
 * @createTime 2023/09/30/ 23:27
 */
@FunctionalInterface
public interface CacheLoader<T> {

    /**
     * 加载缓存
     */
    T load();
}
