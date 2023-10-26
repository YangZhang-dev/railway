package com.zzys.railway.framework.starter.distributedid.core.serviceid;

import com.zzys.railway.framework.starter.distributedid.core.IdGenerator;

/**
 * 业务id生成器
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 16:18
 */
public interface ServiceIdGenerator extends IdGenerator {


    /**
     * 根据 {@param serviceId} 生成雪花算法 ID
     */
    default String nextIdStr(String serviceId) {
        return null;
    }


    /**
     * 解析雪花算法
     */
//    SnowflakeIdInfo parseSnowflakeId(long snowflakeId);
}
