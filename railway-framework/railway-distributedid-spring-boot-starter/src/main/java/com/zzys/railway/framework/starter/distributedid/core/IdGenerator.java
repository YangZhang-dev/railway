package com.zzys.railway.framework.starter.distributedid.core;

/**
 * id生成器
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 15:51
 */
public interface IdGenerator {

    /**
     * 下一个 ID
     */
    default long nextId() {
        return 0L;
    }

    /**
     * 下一个 ID 字符串
     */
    default String nextIdStr() {
        return "";
    }
}
