package com.zzys.railway.framework.starter.cache.core.component;

/**
 * CacheLoader获取数据为空
 *
 * @author YangZhang
 * @createTime 2023/09/30/ 23:27
 */
@FunctionalInterface
public interface CacheGetIfAbsent<T> {

    /**
     * 如果查询结果为空，执行逻辑
     */
    void execute(T param);
}
